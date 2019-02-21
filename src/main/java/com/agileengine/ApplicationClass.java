package com.agileengine;

public class ApplicationClass {

    private static final String BUTTON_ID = "make-everything-ok-button";

    public static void main(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("You have to provide two file paths: origin and sample to compare!");
        }

        String originalId;
        if (args.length == 3) {
            originalId = args[2];
        } else {
            originalId = BUTTON_ID;
        }

        SimilarElementsSearchService service = new SimilarElementsSearchService();

        String result = service.findElement(args[0], args[1], originalId);
        System.out.println(result);
    }
}
