package vn.vti.moneypig.dto;
import vn.vti.moneypig.models.Currency;
import java.util.List;
public class CurrencyResult {
    private Long date;
    private List<Currency> currencyList;
    public Long getDate() {
        return date;
    }
    public void setDate(Long date) {
        this.date = date;
    }
    public List<Currency> getCurrencyList() {
        return currencyList;
    }
    public void setCurrencyList(List<Currency> currencyList) {
        this.currencyList = currencyList;
    }
}
