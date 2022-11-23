public enum VideoType {
    VHS, CD, DVD;

    public static VideoType get(int index) {
        switch (index) {
            case 1:
                return VHS;
            case 2:
                return CD;
            case 3:
                return DVD;
        }
        return null;
    }
}
