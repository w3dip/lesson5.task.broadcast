package ru.sberbank.lesson5.task.broadcast;

class StateManager {

    private static StateManager instance;

    private State state;

    private StateManager() {
    }

    static StateManager getInstance() {
        if (instance == null) {
            instance = new StateManager();
        }
        return instance;
    }

    void changeState() {
        state = State.nextState(state);
    }

    String getState() {
        return state.name();
    }

    private enum State {
        A,
        B,
        C,
        D,
        E;

        public static State nextState(State currentState) {
            if (currentState == null) {
                return A;
            }
            switch(currentState) {
                case A:
                    return B;
                case B:
                    return C;
                case C:
                    return D;
                case D:
                    return E;
                default:
                    return A;
            }

        }
    }
}
