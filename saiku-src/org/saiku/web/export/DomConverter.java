package org.saiku.web.export;

class DomConverter {
    public static org.w3c.dom.Document getDom(String html) {
//        ByteArrayInputStream input = new ByteArrayInputStream(html.getBytes());
//        final HtmlCleaner cleaner = createHtmlCleanerWithProperties();
//        DomSerializer doms = new DomSerializer(cleaner.getProperties(), false);
//        try {
//            TagNode node = cleaner.clean(input);
//            return doms.createDOM(node);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
    	throw new RuntimeException("not implemented");
    }

//    private static HtmlCleaner createHtmlCleanerWithProperties() {
//        HtmlCleaner cleaner = new HtmlCleaner();
//        CleanerProperties props = cleaner.getProperties();
//        props.setAdvancedXmlEscape(true);
//        props.setRecognizeUnicodeChars(true);
//        props.setTranslateSpecialEntities(true);
//        return cleaner;
//    }
}