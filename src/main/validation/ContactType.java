package main.validation;

public enum ContactType {
    UNKNOWN {
        public String getName() {
            return "unknown";
        }
    },
    EMAIL {
        public String getName() {
            return "email";
        }
    },
    PHONE {
        public String getName() {
            return "phone";
        }
    },
    JABBER {
        public String getName() {
            return "jabber";
        }
    };

    public abstract String getName();
}
