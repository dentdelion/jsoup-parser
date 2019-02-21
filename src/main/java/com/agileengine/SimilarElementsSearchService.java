package com.agileengine;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static java.util.stream.Collectors.toList;

class SimilarElementsSearchService {

    private static Logger LOGGER = LoggerFactory.getLogger(SimilarElementsSearchService.class);

    private static String CHARSET_NAME = "utf8";

    String findElement(String originalFileName, String sampleFileName, String originalId) {
        Document document = parseDocument(new File(originalFileName)).orElseThrow(IllegalArgumentException::new);

        List<String> originalTokens = Optional.ofNullable(document.getElementById(originalId))
                .map(this::extractTokensFromElement)
                .orElseThrow(() -> new IllegalArgumentException(String.format("There is no original id '%s' found in document", originalId)));

        Document sampleDocument = parseDocument(new File(sampleFileName)).orElseThrow(IllegalArgumentException::new);

        long maxSimilarity = 0L;
        String similarElementPath = "";

        for (Element element : sampleDocument.getAllElements()) {
            List<String> tokens = extractTokensFromElement(element);

            long similarities = findSimilarities(tokens, originalTokens);
            if (similarities > maxSimilarity) {
                maxSimilarity = similarities;
                similarElementPath = element.cssSelector();
            }
        }

        return similarElementPath;
    }


    private long findSimilarities(List<String> first, List<String> second) {
        return first.stream()
                .filter(Objects::nonNull)
                .mapToLong(string -> second.stream().filter(str -> str.contains(string)).count())
                .sum();
    }

    private List<String> extractTokensFromElement(Element element) {
        return element.attributes().asList()
                .stream()
                .flatMap(a -> Arrays.stream(a.getValue().split("[^\\w]+")))
                .collect(toList()); // split by everything except letters
    }


    private Optional<Document> parseDocument(File htmlFile) {
        try {
            Document doc = Jsoup.parse(
                    htmlFile,
                    CHARSET_NAME,
                    htmlFile.getAbsolutePath());

            return Optional.of(doc);
        } catch (IOException e) {
            LOGGER.error("Error reading [{}] file", htmlFile.getAbsolutePath(), e);
            return Optional.empty();
        }
    }
}
