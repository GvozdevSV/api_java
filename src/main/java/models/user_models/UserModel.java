package models.user_models;

public class UserModel {
    private UserData data;
    private SupportInfo support;

    // Геттеры и сеттеры
    public UserData getData() {
        return data;
    }

    public void setData(UserData data) {
        this.data = data;
    }

    public SupportInfo getSupport() {
        return support;
    }

    public void setSupport(SupportInfo support) {
        this.support = support;
    }
}
