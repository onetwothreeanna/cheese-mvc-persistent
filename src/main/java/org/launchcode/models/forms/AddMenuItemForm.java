package org.launchcode.models.forms;

import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;

import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

/**
 * Created by AnnaYoungyeun on 6/24/17.
 */
public class AddMenuItemForm {

    //fields
    private Menu menu;

    private Iterable<Cheese> cheeses;

    @NotNull
    private int menuId;

    @NotNull
    private int cheeseId;

    //constructors

    public AddMenuItemForm(Menu menu, Iterable<Cheese> cheeses) {
        this.menu = menu;
        this.cheeses = cheeses;
    }

    public AddMenuItemForm(){    }


    //getters & setters

    public Menu getMenu() {  return menu;  }

    public void setMenu(Menu menu) {  this.menu = menu;  }

    public Iterable<Cheese> getCheeses() {  return cheeses;  }

    public void setCheeses(Iterable<Cheese> cheeses) {  this.cheeses = cheeses;  }

    public int getMenuId() {  return menuId;  }

    public int getCheeseId() {  return cheeseId;  }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public void setCheeseId(int cheeseId) {
        this.cheeseId = cheeseId;
    }
}
