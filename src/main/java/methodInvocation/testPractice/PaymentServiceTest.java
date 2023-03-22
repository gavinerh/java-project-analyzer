package methodInvocation.testPractice;

public class PaymentServiceTest {
    private PaymentService service;

    public static void beforeClass() {
        // Called in the beginning of the test suite only once
        // Used for all tests need to share computationally expensive setup
        System.out.println("beforeClass is run");
    }

    public void setupTest() {
        // Called before every test
        // Used for setting up resource before every test
        System.out.println("Set up before test");
    }

    public void testCreditCardPayment() {
        // Test case 1
        System.out.println("Testing credit card payment");
    }

    public void testWireTransfer() {
        // Test case 2
        System.out.println("Test wire transfer functionality");
    }

    public void testInsufficientFunds() {
        // Test case 3
        System.out.println("Test insufficient funds");
//        return false;
    }

    public static void afterClass() {
        // Called once in the end of the entire test suite
        // Used for closing and cleaning up common resources
        System.out.println("Cleaning up after tests");
    }
}
