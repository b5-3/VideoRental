public enum PriceCode {
    REGULAR, NEW_RELEASE;

    public static PriceCode get(int index) {
        switch (index) {
            case 1:
                return REGULAR;
            case 2:
                return NEW_RELEASE;
        }
        return null;
    }
}
