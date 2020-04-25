package p1.store;

import p1.Entity.Customer;

import java.util.ArrayList;
import java.util.Scanner;

public class Store {
    private static State state;

    public static State createStore() {
        state = new State();
        state.currentNode = Node.START;
        state.possibleActions = new ArrayList<>();

        state.possibleActions.add(Action.START_END);
        state.possibleActions.add(Action.START_SHOP);
        state.possibleActions.add(Action.START_REPORT);

        state.customers = new ArrayList<>();

        return state;
    }

    public static void dispatch(Action action) {
        switch (action) {
            case START_SHOP:
                start_shop();
                break;
            case START_REPORT:
                start_report();
                break;
            case SHOP_START:
                shop_start();
                break;
            case SHOP_BUY:
                shop_buy();
                break;
            case SHOP_CART:
                shop_cart();
                break;
            case SHOP_CHECKOUT:
                shop_checkout();
                break;
            case BUY_SHOP:
                buy_shop();
                break;
            case BUY_CART:
                buy_cart();
                break;
            case CART_SHOP:
                cart_shop();
                break;
            case CART_CHECKOUT:
                cart_checkout();
                break;
            case CHECKOUT_START:
                checkout_start();
                break;
            case CHECKOUT_SHOP:
                checkout_shop();
                break;
            case REPORT_START:
                report_start();
                break;
            case START_END:
            case SHOP_END:
            case REPORT_END:
                end();
        }
    }

    public static State getState() {
        return state;
    }

    private static ArrayList<Action> getStartPossibleActionTypes() {
        ArrayList<Action> possibleActions = new ArrayList<>();

        possibleActions.add(Action.START_END);
        possibleActions.add(Action.START_SHOP);
        possibleActions.add(Action.START_REPORT);

        return possibleActions;
    }

    private static ArrayList<Action> getShopPossibleActionTypes() {
        ArrayList<Action> possibleActions = new ArrayList<>();

        possibleActions.add(Action.SHOP_END);
        possibleActions.add(Action.SHOP_START);
        possibleActions.add(Action.SHOP_BUY);
        possibleActions.add(Action.SHOP_CART);
        possibleActions.add(Action.SHOP_CHECKOUT);

        return possibleActions;
    }

    private static ArrayList<Action> getReportPossibleActionTypes() {
        ArrayList<Action> possibleActions = new ArrayList<>();

        possibleActions.add(Action.REPORT_END);
        possibleActions.add(Action.REPORT_START);

        return possibleActions;
    }

    private static ArrayList<Action> getBuyPossibleActionTypes() {
        ArrayList<Action> possibleActions = new ArrayList<>();

        possibleActions.add(Action.BUY_SHOP);
        possibleActions.add(Action.BUY_CART);

        return possibleActions;
    }

    private static ArrayList<Action> getCartPossibleActionTypes() {
        ArrayList<Action> possibleActions = new ArrayList<>();

        possibleActions.add(Action.CART_SHOP);
        possibleActions.add(Action.CART_CHECKOUT);

        return possibleActions;
    }

    private static ArrayList<Action> getCheckoutPossibleActionTypes() {
        ArrayList<Action> possibleActions = new ArrayList<>();

        possibleActions.add(Action.CHECKOUT_SHOP);
        possibleActions.add(Action.CHECKOUT_START);

        return possibleActions;
    }

    private static boolean confirm(String message) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(message);
        System.out.println("[0] Anuluj");
        System.out.println("[1] Potwierdź");

        String in = scanner.nextLine();

        return in.equals("1");
    }

    private static void start_shop() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj imię:");
        String customerName = scanner.nextLine();
        Customer currentCustomer = null;

        for (Customer customer : state.customers) {
            if (customer.getName().equals(customerName)) {
                currentCustomer = customer;
            }
        }

        if (currentCustomer == null) {
             currentCustomer = new Customer(customerName);
             state.customers.add(currentCustomer);
        }

        state.currentCustomer = currentCustomer;
        state.currentNode = Node.SHOP;
        state.possibleActions = getShopPossibleActionTypes();
    }

    private static void start_report() {
        state.currentNode = Node.REPORT;
        state.possibleActions = getReportPossibleActionTypes();
    }

    private static void shop_start() {
        if (!confirm("Czy na pewno chcesz wyjść ze sklepu?")) return;

        state.currentNode = Node.START;
        state.possibleActions = getStartPossibleActionTypes();
        state.currentCustomer = null;
    }

    private static void shop_buy() {
        state.currentNode = Node.BUY;
        state.possibleActions = getBuyPossibleActionTypes();
    }

    private static void shop_cart() {
        state.currentNode = Node.CART;
        state.possibleActions = getCartPossibleActionTypes();
    }

    private static void shop_checkout() {
        state.currentNode = Node.CHECKOUT;
        state.possibleActions = getCheckoutPossibleActionTypes();
    }

    private static void buy_shop() {
        state.currentNode = Node.SHOP;
        state.possibleActions = getShopPossibleActionTypes();
    }

    private static void buy_cart() {
        state.currentNode = Node.CART;
        state.possibleActions = getCartPossibleActionTypes();
    }

    private static void cart_shop() {
        state.currentNode = Node.SHOP;
        state.possibleActions = getShopPossibleActionTypes();
    }

    private static void cart_checkout() {
        state.currentNode = Node.CHECKOUT;
        state.possibleActions = getCheckoutPossibleActionTypes();
    }

    private static void checkout_start() {
        state.currentNode = Node.START;
        state.possibleActions = getStartPossibleActionTypes();
        state.currentCustomer = null;
    }

    private static void checkout_shop() {
        state.currentNode = Node.SHOP;
        state.possibleActions = getShopPossibleActionTypes();
    }

    private static void report_start() {
        state.currentNode = Node.START;
        state.possibleActions = getStartPossibleActionTypes();
    }

    private static void end() {
        if (!confirm("Czy na pewno chcesz zamknąć program?")) return;

        state.isEnd = true;
    }
}