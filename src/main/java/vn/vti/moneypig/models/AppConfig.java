package vn.vti.moneypig.models;


import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "app_config")
public class AppConfig {
    public AppConfig(Long id, int active) {
        this.id = id;
        this.active = active;
    }

    public AppConfig(){


    }
    public AppConfig(int active) {
        this.active = active;
    }

    private  Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private  int active = 1;

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
