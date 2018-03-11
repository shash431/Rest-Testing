package support;

public class NoReplacementsSpecifiedException extends Exception {

    private static final String NO_REPLACEMENTS_SPECIFIED = "No replacements specified.";

    NoReplacementsSpecifiedException() {    }

    private NoReplacementsSpecifiedException(String str) {
        super(str);
    }

    @Override
    public String getMessage() {
        return NO_REPLACEMENTS_SPECIFIED;
    }

    public static void main(String[] args)
    {
        try {
            throw new NoReplacementsSpecifiedException(NO_REPLACEMENTS_SPECIFIED);
        } catch (NoReplacementsSpecifiedException ex) {
            ex.printStackTrace();
        }
    }
}
