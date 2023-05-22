package com.example.airlines365.model.enums;

public enum Seat {

    A1("1A"), B1("1B"), C1("1C"), D1("1D"), E1("1E"), F1("1F"),
    A2("2A"), B2("2B"), C2("2C"), D2("2D"), E2("2E"), F2("2F"),
    A3("3A"), B3("3B"), C3("3C"), D3("3D"), E3("3E"), F3("3F"),
    A4("4A"), B4("4B"), C4("4C"), D4("4D"), E4("4E"), F4("4F"),
    A5("5A"), B5("5B"), C5("5C"), D5("5D"), E5("5E"), F5("5F"),
    A6("6A"), B6("6B"), C6("6C"), D6("6D"), E6("6E"), F6("6F"),
    A7("7A"), B7("7B"), C7("7C"), D7("7D"), E7("7E"), F7("7F"),
    A8("8A"), B8("8B"), C8("8C"), D8("8D"), E8("8E"), F8("8F"),
    A9("9A"), B9("9B"), C9("9C"), D9("9D"), E9("9E"), F9("9F");

    private final String value;

    Seat(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
