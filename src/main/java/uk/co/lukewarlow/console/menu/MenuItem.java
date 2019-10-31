package uk.co.lukewarlow.console.menu;

/**
 * Class for menu item.
 */
public class MenuItem
{
    /**
     * The unique (per menu) id of the menu item.
     */
    private long id;

    /**
     * The description for the menu item.
     */
    private String description;

    /**
     * The submenu, null if there is no submenu.
     *
     * @see AbstractMenu
     */
    private AbstractMenu menu;

    /**
     * The action, null if there is a submenu.
     *
     * @see Runnable
     */
    private Runnable action;

    /**
     * Whether the menu item is visible. True by default.
     */
    private boolean isVisible;

    /**
     * Whether the menu item is the exit option. False by default.
     */
    private boolean isExitOption;

    MenuItem(final long id)
    {
        this.id = id;
    }

    /**
     * Constructor for menu item, if it is an action.
     *
     * @param id The unique (per menu) id for the item.
     * @param description The description for the item.
     * @param action The action that runs when the item is selected.
     */
    public MenuItem(final long id, final String description, final Runnable action)
    {
        this.id = id;
        this.description = description;
        this.action = action;
        this.menu = null;
        show();
    }

    /**
     * Constructor for menu item, if it is a submenu.
     * @param id The unique (per menu) id for the item.
     * @param description The description for the item.
     * @param menu        The submenu.
     */
    public MenuItem(final long id, final String description, final AbstractMenu menu)
    {
        this.id = id;
        this.description = description;
        this.menu = menu;
        this.action = null;
        show();
    }

    /**
     * Gets the id for the item.
     *
     * @return The id for the item.
     */
    public final long getID()
    {
        return id;
    }

    /**
     * Gets the description for the item, this is what's printed to the console.
     *
     * @return The description for the item.
     */
    public final String getDescription()
    {
        return description;
    }

    /**
     * Gets whether the item is visible.
     *
     * @return Whether the menu item is visible.
     */
    public final boolean isVisible()
    {
        return isVisible;
    }

    /**
     * Hides menu item.
     *
     * @return this MenuItem object.
     */
    public final MenuItem hide()
    {
        isVisible = false;
        return this;
    }

    /**
     * Shows menu item.
     *
     * @return the current instance of the MenuItem.
     */
    public final MenuItem show()
    {
        isVisible = true;
        return this;
    }

    /**
     * Make the menu item exit the current menu.
     *
     * @return the current instance of the MenuItem.
     */
    public final MenuItem setAsExitOption()
    {
        isExitOption = true;
        return this;
    }

    /**
     * Should be overridden if there is no submenu.
     *
     * @return whether the menu should keep looping, always true, other than for the exit option.
     */
    boolean run()
    {
        if (menu != null) menu.display();
        else if (action != null) action.run();
        return !isExitOption;
    }

    @Override
    public final boolean equals(final Object o)
    {
        if (o == this) return true;
        if (!(o instanceof MenuItem)) return false;
        var menuItem = (MenuItem) o;
        return menuItem.getID() == getID();
    }

    @Override
    public final int hashCode()
    {
        return getDescription().hashCode();
    }
}

