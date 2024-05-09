import java.util.Scanner;

public abstract class Customer implements CustomerInterface
{								// abstract applied

  private int adultQuantity;
  private int childQuantity;
  private int studentQuantity;
  private static final double ADULT_PRICE = 17.00;	// a fixed price that cannot be change

  // no args constructor
  public Customer ()
  {
  }

  // constructor with three parameters
  public Customer (int adultQauntity, int childQuantity, int studentQuantity)
  {
	this.adultQuantity = adultQauntity;
	this.childQuantity = childQuantity;
	this.studentQuantity = studentQuantity;
  }

  // no body where it is an abstract method
  public abstract double calTotalPrice ();

  // getter and setter
  protected int getAdultQuantity ()
  {
	return adultQuantity;
  }

  protected int getChildQuantity ()
  {
	return childQuantity;
  }

  protected int getStudentQuantity ()
  {
	return studentQuantity;
  }

  protected void setAdultQuantity (int adultQuantity)
  {
	this.adultQuantity = adultQuantity;
  }

  protected void setChildQuantity (int childQuantity)
  {
	this.childQuantity = childQuantity;
  }

  protected void setStudentQuantity (int studentQuantity)
  {
	this.studentQuantity = studentQuantity;
  }

  // method that calculate every category of price
  @Override
  public double calPrice ()
  {
	return ADULT_PRICE;
  }

  // to display the quantity of tickets left
  public void ticketsLeft (int qty)
  {
	System.out.println ("\nTotal tickets left: " + qty);
  }

  // validate whether user want to continue buying or not with condition y or n
  public Boolean askCustomer ()
  {
	Scanner sc = new Scanner (System.in);
	boolean cont = true;
	char confirm = 'Y';

	System.out.print ("\nAny more customers? (y / n) : ");
	confirm = sc.next ().charAt (0);	// get character input (y / n)
	confirm = Character.toUpperCase (confirm);	// make char input uppercase
	switch (confirm)
	  {
	  case 'Y':
		cont = true;
		break;
	  case 'N':
		cont = false;
		break;
	  default:
		System.out.println ("Invalid input. Please try again.");
		cont = askCustomer ();	// recursion
		break;
	  }
	return cont;
  }
}
