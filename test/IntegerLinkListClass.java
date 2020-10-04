package test;
    public class IntegerLinkListClass<Integer> extends LinkListClass<Integer> {

        private java.lang.Integer max;
        private java.lang.Integer min;

        public IntegerLinkListClass() {
            super();
        }

        public IntegerLinkListClass(java.lang.Integer[] array) {
            if (array == null || array.length == 0){
                return;
            }
            max = array[0];
            min = array[0];
            addArraySafe((Integer[]) array, node -> {
                max = (java.lang.Integer) node < max ? max : (java.lang.Integer) node;
                min = (java.lang.Integer) node > min ? min : (java.lang.Integer) node;
            }, java.lang.Integer.class);
        }

        public java.lang.Integer maxValue() {
            return max;
        }

        public java.lang.Integer minValue() {
            return min;
        }
    }

