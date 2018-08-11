package uk.co.lukewarlow.console.menu;

public final class TestSubMenu extends AbstractMenu
{
    TestSubMenu()
    {
        super("Welcome to the test sub menu.");
    }

    @Override
    protected void init()
    {
        addMenuItem(new MenuItem(0, "Exit current menu", () -> {}).isExitOption());
        addMenuItem(new MenuItem(1, "Test sub menu item", () -> System.out.println("Test sub menu item selected")));
    }
}
