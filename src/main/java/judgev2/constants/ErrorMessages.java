package judgev2.constants;

public class ErrorMessages {

    public static final String INVALID_USERNAME =
            "Username must be between 2 and 10 characters";

    public static final String INVALID_PASSWORD =
            "Password must must be between 3 and 10 characters";

    public static final String INVALID_EMAIL =
            "Enter a valid email address: ex. 'user@mail.com'";

    public static final String INVALID_GITHUB_URL =
            "Enter git address following this pattern: 'https://github.com/{username}/{dir}/…'";

    public static final String ENTITY_NOT_EXISTS =
            "%s '%s' does not exists!";

    public static final String INVALID_EXERCISE_NAME =
            "Exercise name length must be more than 2 characters";

    public static final String INVALID_START_DATE =
            "The date cannot be in the future!";

    public static final String INVALID_DUE_DATE =
            "The date cannot be in the past!";
}
