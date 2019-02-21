### Similarities parser 

####Results

```
java -jar build/libs/ae-backend-xml-java-snippets-0.0.1.jar ./samples/sample-0-origin.html ./samples/sample-1-evil-gemini.html 
#page-wrapper > div.row:nth-child(3) > div.col-lg-8 > div.panel.panel-default > div.panel-body > a.btn.btn-success

java -jar build/libs/ae-backend-xml-java-snippets-0.0.1.jar ./samples/sample-0-origin.html ./samples/sample-2-container-and-clone.html 
#page-wrapper > div.row:nth-child(3) > div.col-lg-8 > div.panel.panel-default > div.panel-body > div.some-container > a.btn.test-link-ok

java -jar build/libs/ae-backend-xml-java-snippets-0.0.1.jar ./samples/sample-0-origin.html ./samples/sample-3-the-escape.html 
#page-wrapper > div.row:nth-child(3) > div.col-lg-8 > div.panel.panel-default > div.panel-footer > a.btn.btn-success

 java -jar build/libs/ae-backend-xml-java-snippets-0.0.1.jar ./samples/sample-0-origin.html ./samples/sample-4-the-mash.html 
#page-wrapper > div.row:nth-child(3) > div.col-lg-8 > div.panel.panel-default > div.panel-footer > a.btn.btn-success
```

Also it is possible to pass another id value as third argument