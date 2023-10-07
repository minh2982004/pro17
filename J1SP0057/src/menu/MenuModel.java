package menu;

public class MenuModel {
    private String[] menuItems = {
        "Create a new account.",
        "Login system.",
        "Exit"
    };

    private int selectedMenuItem;

    public String[] getMenuItems() {
        return menuItems;
    }

    public void setSelectedMenuItem(int selectedMenuItem) {
        this.selectedMenuItem = selectedMenuItem;
    }

    public int getSelectedMenuItem() {
        return selectedMenuItem;
    }
}
