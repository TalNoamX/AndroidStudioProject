package com.example.busy.restaurant.Rforms;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;



public class menu_form {
    private ArrayList<dish_form> starters_list;
    private ArrayList<dish_form> main_list;
    private ArrayList<dish_form> drink_list;
    private ArrayList<dish_form> deserts_list;




    public menu_form() {
        starters_list = new ArrayList<dish_form>();
        main_list = new ArrayList<dish_form>();
        drink_list = new ArrayList<dish_form>();
        deserts_list = new ArrayList<dish_form>();

    }

    public void setStarters_list(ArrayList<dish_form> starters_list) {
        this.starters_list.addAll(starters_list);
    }

    public void setMain_list(ArrayList<dish_form> main_list) {
        this.main_list.addAll(main_list);
    }

    public void setDrink_list(ArrayList<dish_form> drink_list) {
        this.drink_list.addAll(drink_list);
    }

    public void setDeserts_list(ArrayList<dish_form> deserts_list) {
        this.deserts_list.addAll(deserts_list);
    }



    public ArrayList<dish_form> getStarters_list() {
        return starters_list;
    }

    public ArrayList<dish_form> getMain_list() {
        return main_list;
    }

    public ArrayList<dish_form> getDrink_list() {
        return drink_list;
    }

    public ArrayList<dish_form> getDeserts_list() {
        return deserts_list;
    }
    public boolean exist(dish_form dish){
        for (int i = 0; i < this.deserts_list.size(); i++){
            if (deserts_list.get(i).check_equal(dish)){
                return true;
            }
        }
        for (int i = 0; i < main_list.size(); i++){
            if(main_list.get(i).check_equal(dish)){
                return true;
            }
        }
        for (int i = 0; i < drink_list.size(); i++){
            if (drink_list.get(i).check_equal(dish)){
                return true;
            }
        }
        for (int i = 0; i < starters_list.size(); i++){
            if(starters_list.get(i).check_equal(dish)){
                return true;
            }
        }

            return false;

    }
    public boolean add_start_dish(dish_form dish){
      return starters_list.add(dish);
    }
    public boolean add_main_dish(dish_form dish){
       return main_list.add(dish);

    }
    public boolean add_drink(dish_form drink){
        return drink_list.add(drink);
    }
    public boolean add_desert_dish(dish_form desert){
        return deserts_list.add(desert);
    }
    public int get_numof_dishes(){
        int sum = starters_list.size() + drink_list.size() + main_list.size() + deserts_list.size();
        return sum;
    }
    public boolean remove_dish(String name, String type){
        if (type == "Starters"){
            for (int i = 0; i < starters_list.size(); i++){
                String str = starters_list.get(i).getDish_name();
                if (str.equals(name)){
                    dish_form temp = starters_list.get(i);
                    starters_list.remove(temp);
                    return true;
                }
            }
            return false;
        }
        else if (type == "Mains"){
            for (int i = 0; i < main_list.size(); i++){
                String str = main_list.get(i).getDish_name();
                if (str.equals(name)){
                    dish_form temp = main_list.get(i);
                    main_list.remove(temp);
                    return true;
                }
            }
            return false;
        }
        else if (type == "Drink"){
            for (int i = 0; i < drink_list.size(); i++){
                String str = drink_list.get(i).getDish_name();
                if (str.equals(name)){
                    dish_form temp = drink_list.get(i);
                    drink_list.remove(temp);
                    return true;
                }
            }
            return false;
        }
        else if (type == "Deserts"){
            for (int i = 0; i < deserts_list.size(); i++){
                String str = deserts_list.get(i).getDish_name();
                if (str.equals(name)){
                    dish_form temp = deserts_list.get(i);
                    deserts_list.remove(temp);
                    return true;
                }
            }
            return false;
        }
        else{
            return false;
        }
    }
    public void clear_starters(){
        starters_list.clear();
    }
    public void clear_mains(){
        main_list.clear();
    }
    public void clear_drink(){
        drink_list.clear();
    }
    public void clea_deserts(){
        deserts_list.clear();
    }
    public void replace_dish(String name ,dish_form dish){
        if (starters_list.contains(dish)){
            for (int i = 0; i < starters_list.size(); i++){
                if (starters_list.get(i).getDish_name().equals(name)){
                    starters_list.set(i, dish);
                }
            }
        }
       else if (main_list.contains(dish)){
            for (int i = 0; i < main_list.size(); i++){
                if (main_list.get(i).getDish_name().equals(name)){
                    main_list.set(i, dish);
                }
            }
        }
        else if (drink_list.contains(dish)){
            for (int i = 0; i < drink_list.size(); i++){
                if (drink_list.get(i).getDish_name().equals(name)){
                    drink_list.set(i, dish);
                }
            }
        }
        else if (deserts_list.contains(dish)){
            for (int i = 0; i < deserts_list.size(); i++){
                if (deserts_list.get(i).getDish_name().equals(name)){
                    deserts_list.set(i, dish);
                }
            }
        }
    }
}
