import java.io.*;

class SendResponse {
    static void sendOkResponse(OutputStream output, InputStream fis, String ext) throws Exception {
        Util.writeLine(output, "HTTP/1.1 200 OK");
        Util.writeLine(output, "Date:" + Util.getDateStringUtc());
        Util.writeLine(output, "Server: Marutanix/2.0");
        Util.writeLine(output, "Connection: close");
        Util.writeLine(output, "Content-type: " + Util.getContentType(ext));
        Util.writeLine(output, "");

        int ch;
        while((ch = fis.read()) != -1) {
            output.write(ch);
        }
    }

    static void sendMovePermanentlyResponse(OutputStream output, String location) throws Exception {
        Util.writeLine(output, "HTTP/1.1 301 MovedPermanently");
        Util.writeLine(output, "Date:" + Util.getDateStringUtc());
        Util.writeLine(output, "Server: Marutanix/2.0");
        Util.writeLine(output, "Location:" + location);
        Util.writeLine(output, "Connection: close");
        Util.writeLine(output, "");
    }

    static void sendNotFoundResponse(OutputStream output, String errorDocumentRoot) throws Exception {
        Util.writeLine(output, "HTTP/1.1 404 NotFound");
        Util.writeLine(output, "Date:" + Util.getDateStringUtc());
        Util.writeLine(output, "Server: Marutanix/2.0");
        Util.writeLine(output, "Connection: close");
        Util.writeLine(output, "Contenttype: text/html");
        Util.writeLine(output, "");
        
        try(InputStream fis = new BufferedInputStream(new FileInputStream(errorDocumentRoot + "/404.html"))) {
            int ch;
            while((ch = fis.read()) != -1) {
                output.write(ch);
            }
        }
    }

}