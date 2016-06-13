package com.mnubo.java.sdk.client.models.result;

import static com.mnubo.java.sdk.client.Constants.PRINT_OBJECT_NULL;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class Result {

    /**
     * Result states values
     */
    public enum ResultStates {

        success("success"), 
        error("error");

        private final String resultState;

        private ResultStates(String state) {
            resultState = state;
        }

        public boolean equalsResultState(String otherState) {
            return (otherState == null) ? false : resultState.equals(otherState);
        }

        @Override
        public String toString() {
            return this.resultState;
        }
    }

    /**
     * Constant key used during the deserialization and serialization of json files.
     */
    public static final String ID = "id";

    /**
     * Constant key used during the deserialization and serialization of json files.
     */
    public static final String RESULT = "result";

    /**
     * Constant key used during the deserialization and serialization of json files.
     */
    public static final String MESSAGE = "message";

    /**
     * Constant key used during the deserialization and serialization of json files.
     */
    public static final String OBJECT_EXISTS = "objectExists";

    private final String id;
    private final ResultStates result;
    private final String message;
    private final boolean objectExists;

    /**
     * Constructor
     * @param id Result's ID
     * @param result result, Success or error
     * @param message message
     * @param objectExists is exists
     */
    @JsonCreator
    public Result(@JsonProperty(ID) String id,
                  @JsonProperty(RESULT) ResultStates result,
                  @JsonProperty(MESSAGE) String message,
                  @JsonProperty(OBJECT_EXISTS) boolean objectExists) {
        this.id = id;
        this.result = result;
        this.message = message;
        this.objectExists = objectExists;
    }

    public String getId() {
        return id;
    }

    public ResultStates getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public boolean isObjectExists() {
        return objectExists;
    }

    @Override
    public String toString() {
        StringBuilder toPrint = new StringBuilder();
        toPrint.append("{\n");
        toPrint.append(line2String("id", id));
        toPrint.append(line2String("result", result));
        toPrint.append(line2String("message", message));
        toPrint.append("}\n");
        return toPrint.toString();
    }

    private String line2String(String name, Object value) {
        StringBuilder build = new StringBuilder();
        if (name != null) {
            build.append("     " + name + " : ");
            if (value != null) {
                build.append(value);
            }
            else {
                build.append(PRINT_OBJECT_NULL);
            }
            build.append("\n");
        }
        return build.toString();
    }
}
