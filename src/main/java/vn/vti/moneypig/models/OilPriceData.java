package vn.vti.moneypig.models;

import java.util.Arrays;

public class OilPriceData {
    private OilPrice[] data;

    public OilPrice[] getData() {
        return data;
    }

    public void setData(OilPrice[] data) {
        this.data = data;
    }


// Getters and setters

    public static class Data {
        private String oil;
        private String price;
        private String increaseAndDecrease;
        private String percent;

        public String getOil() {
            return oil;
        }

        public void setOil(String oil) {
            this.oil = oil;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getIncreaseAndDecrease() {
            return increaseAndDecrease;
        }

        public void setIncreaseAndDecrease(String increaseAndDecrease) {
            this.increaseAndDecrease = increaseAndDecrease;
        }

        public String getPercent() {
            return percent;
        }

        public void setPercent(String percent) {
            this.percent = percent;
        }

        // Getters and setters

        @Override
        public String toString() {
            return "Data{" +
                    "oil='" + oil + '\'' +
                    ", price='" + price + '\'' +
                    ", increaseAndDecrease='" + increaseAndDecrease + '\'' +
                    ", percent='" + percent + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "OilPriceData{" +
                "data=" + Arrays.toString(data) +
                '}';
    }
}