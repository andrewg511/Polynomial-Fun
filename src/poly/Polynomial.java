package poly;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.time.Clock;
import java.util.Scanner;
/**
 * This class implements evaluate, add and multiply for polynomials.
 * 
 * @author runb-cs112
 *
 */
public class Polynomial {
	
	/**
	 * Reads a polynomial from an input stream (file or keyboard). The storage format
	 * of the polynomial is:
	 * <pre>
	 *     <coeff> <degree>
	 *     <coeff> <degree>
	 *     ...
	 *     <coeff> <degree>
	 * </pre>
	 * with the guarantee that degrees will be in descending order. For example:
	 * <pre>
	 *      4 5
	 *     -2 3
	 *      2 1
	 *      3 0
	 * </pre>
	 * which represents the polynomial:
	 * <pre>
	 *      4*x^5 - 2*x^3 + 2*x + 3 
	 * </pre>
	 * 
	 * @param sc Scanner from which a polynomial is to be read
	 * @throws IOException If there is any input error in reading the polynomial
	 * @return The polynomial linked list (front node) constructed from coefficients and
	 *         degrees read from scanner
	 */
	public static Node read(Scanner sc) 
	throws IOException {
		Node poly = null;
		while (sc.hasNextLine()) {
			Scanner scLine = new Scanner(sc.nextLine());
			poly = new Node(scLine.nextFloat(), scLine.nextInt(), poly);
			scLine.close();
		}
		return poly;
	}
	
	/**
	 * Returns the sum of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list
	 * @return A new polynomial which is the sum of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node add(Node poly1, Node poly2) {
		/** COMPLETE THIS METHOD **/

		Node newPoly = new Node(0,0,null);

		while (!(poly1 == null && poly2 == null) ) {

			if(poly1 == null && poly2 != null){

				newPoly = new Node(poly2.term.coeff, poly2.term.degree, newPoly );
				poly2 = poly2.next;


			} else if(poly2 == null  && poly1 !=null){

				newPoly = new Node(poly1.term.coeff, poly1.term.degree, newPoly);
				poly1  = poly1.next;

			} else if(poly1.term.degree > poly2.term.degree){ /**if Poly1 is greater than poly2*/

				newPoly = new Node(poly2.term.coeff,poly2.term.degree,newPoly);
				poly2 = poly2.next;

			} else if(poly1.term.degree < poly2.term.degree){ /**if Poly2 is greater than poly1*/

				newPoly = new Node(poly1.term.coeff,poly1.term.degree,newPoly);
				poly1 = poly1.next;


			} else if(poly1.term.degree == poly2.term.degree){ /**if both Polynomial degrees are the same*/

				newPoly = new Node(poly1.term.coeff + poly2.term.coeff,poly1.term.degree,newPoly);
				poly1 = poly1.next;
				poly2 = poly2.next;

			}



		}

		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION


		Node previousNode=null;
		Node nextNode;
		while(newPoly!=null)
		{
			nextNode=newPoly.next;
			// reversing the link
			newPoly.next=previousNode;
			// moving currentNode and previousNode by 1 node
			previousNode=newPoly;
			newPoly=nextNode;
		}
		previousNode = previousNode.next;
		return previousNode;
	}
	
	/**
	 * Returns the product of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list)
	 * @return A new polynomial which is the product of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node multiply(Node poly1, Node poly2) {
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION

		Node newPoly1 = poly1;
		Node newPoly2 = poly2;
		Node last = null;
		Node front = null;
		Node previousNodes = null;
		while(newPoly1 != null){

			if(newPoly2 == null){

				newPoly2 = poly2;

			}
			while(newPoly2 != null){

				Node ptr = new Node((newPoly1.term.coeff*newPoly2.term.coeff), (newPoly1.term.degree + newPoly2.term.degree), null);
				previousNodes = add(previousNodes, ptr);
				if(last != null){

					last.next = ptr;

				}else {

					front = ptr;
				}
				last = ptr;
				newPoly2 = newPoly2.next;
			}

			newPoly1 = newPoly1.next;
		}
		// order front nodes in order of degree
		// add like degrees term

		return previousNodes;


		/*Node termToAdd;
		Node previousNodes = new Node(0,0,null);

		while(poly2 != null){



			while(poly1 != null){

				termToAdd = new Node(poly1.term.coeff * poly2.term.coeff, poly1.term.degree+poly2.term.degree, null);
				previousNodes = add(previousNodes, termToAdd);



				poly1 = poly1.next;


			}


			poly2 = poly2.next;

		}

		return previousNodes;
*/
	}





	/**
	 * Evaluates a polynomial at a given value.
	 * 
	 * @param poly Polynomial (front of linked list) to be evaluated
	 * @param x Value at which evaluation is to be done
	 * @return Value of polynomial p at x
	 */
	public static float evaluate(Node poly, float x) {
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
		float sum = 0;
		while(poly != null){

			sum += (float)(Math.pow(x, poly.term.degree))*poly.term.coeff;

			poly = poly.next;
		}

		return sum;
	}
	
	/**
	 * Returns string representation of a polynomial
	 * 
	 * @param poly Polynomial (front of linked list)
	 * @return String representation, in descending order of degrees
	 */
	public static String toString(Node poly) {
		if (poly == null) {
			return "0";
		} 
		
		String retval = poly.term.toString();
		for (Node current = poly.next ; current != null ;
		current = current.next) {
			retval = current.term.toString() + " + " + retval;
		}
		return retval;
	}	
}
