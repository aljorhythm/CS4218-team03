package sg.edu.nus.comp.cs4218.impl.util;

public class IORedirectionObserver {
    private boolean isRedirection;
    private String checkString = "";
    private IORedirectionHandler ioRedirectionHandler;

    public IORedirectionObserver(IORedirectionHandler ioRedirectionHandler) {
        this.ioRedirectionHandler = ioRedirectionHandler;
    }

    public void testRedirection() {
        ioRedirectionHandler.testRedirection(this);
    }

    public void setCheckString(String checkString) {this.checkString = checkString;}

    public String getCheckString() {return checkString;}

    public void setIsRedirection(boolean isRedirection) {this.isRedirection = isRedirection;}

    public boolean getIsRedirection() {return isRedirection;}

}
