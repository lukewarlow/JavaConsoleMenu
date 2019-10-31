package uk.co.lukewarlow.console.menu;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Class that stores menu data.
 */
public abstract class AbstractMenu
{
    private String title;

    /**
     * The list of menu items.
     *
     * @see MenuItem
     */
    private List<MenuItem> menuItems;

    /**
     *
     * AbstractMenu constructor.
     *
     * @param title The title of the menu that is printed to the console.
     */
    protected AbstractMenu(String title)
    {
        this.title = title;
        menuItems = new ArrayList<>();
        init();
    }

    /**
     * Used to add the relevant menu items.
     */
    protected abstract void init();

    /**
     * Updates menu items depending on what data has been entered.
     */
    protected void updateMenuItems() {}

    /**
     * Displays the menu.
     */
    public void display()
    {
        var scanner = new Scanner(System.in);
        var repeat = true;
        while (repeat)
        {
            updateMenuItems();
            System.out.println();
            System.out.println(title);
            for (var i = 0; i < menuItems.size(); i++)
            {
                if (menuItems.get(i).isVisible())
                    System.out.println(i + ". " + menuItems.get(i).getDescription());
            }
            System.out.print("Select Option: ");
            var input = scanner.nextLine();
            try
            {
                var item = Integer.parseInt(input);
                var menuItem = menuItems.get(item);
                if (menuItem.isVisible()) repeat = menuItem.run();
                else throw new InputMismatchException();
            }
            catch (NumberFormatException e)
            {
                System.out.println("Invalid option, you need to enter a number.");
                repeat = true;
            }
            catch (IndexOutOfBoundsException e)
            {
                System.out.println("Invalid option. Option " + input + " doesn't exist.");
                repeat = true;
            }
            catch (InputMismatchException e)
            {
                System.out.println("Invalid option. Option at " + input + " is hidden.");
                repeat = true;
            }
        }
    }

    /**
     * Adds the provided item to the list of menu items.
     * If it's not already there.
     *
     * @param menuItem The item to be added.
     */
    public final void addMenuItem(final MenuItem menuItem)
    {
        if (!menuItems.contains(menuItem)) menuItems.add(menuItem);
        else throw new IllegalArgumentException("Menu item with id " + menuItem.getID() + " already exists!");
    }

    /**
     * Adds the provided item to the list of menu items.
     * If it's not already there.
     *
     * @param menuItem The item to be added.
     */
    public final void addHiddenMenuItem(final MenuItem menuItem)
    {
        if (!menuItems.contains(menuItem)) menuItems.add(menuItem.hide());
    }

    /**
     * Shows the menu item.
     *
     * @param itemId The id for the item to be shown.
     */
    public final void showMenuItem(final long itemId)
    {
        try
        {
            var menuItem = new MenuItem(itemId);
            menuItems.get(menuItems.indexOf(menuItem)).show();
        }
        catch (IndexOutOfBoundsException e)
        {
            throw new IllegalArgumentException("Error showing menu item. Menu item with ID " + itemId + " hasn't been added to this menu.");
        }
    }

    /**
     * Hides the menu item.
     *
     * @param itemId The id for the item to be hidden.
     */
    public final void hideMenuItem(final long itemId)
    {
        try
        {
            var menuItem = new MenuItem(itemId);
            menuItems.get(menuItems.indexOf(menuItem)).hide();
        }
        catch (IndexOutOfBoundsException e)
        {
            throw new IllegalArgumentException("Error hiding menu item. Menu item with ID " + itemId + " hasn't been added to this menu.");
        }
    }
}

