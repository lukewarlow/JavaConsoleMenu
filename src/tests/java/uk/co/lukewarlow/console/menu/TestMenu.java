package uk.co.lukewarlow.console.menu;

public final class TestMenu extends AbstractMenu
{
    private Boolean showHiddenMenu = false;

    TestMenu()
    {
        super("Welcome to the test menu.");
    }

    @Override
    protected void init()
    {
        addMenuItem(new MenuItem(0, "Exit menu", () -> {}).setAsExitOption());
        addMenuItem(new MenuItem(1, "Test submenu", new TestSubMenu()));

        addMenuItem(new MenuItem(2, "Show hidden menu item", () ->
        {
            System.out.println("Showing hidden menu item");
            showHiddenMenu = true;
        }));

        addHiddenMenuItem(new MenuItem(3, "Hidden menu item", () -> System.out.println("I was a hidden menu item")));
    }


    @Override
    protected void updateMenuItems()
    {
        if (showHiddenMenu) showMenuItem(3);
    }
}