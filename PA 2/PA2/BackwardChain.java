//
.// BackwardChain
//
// This class implements a backward chaining inference procedure. The
// implementation is very skeletal, and the resulting reasoning process is
// not particularly efficient. Knowledge is restricted to the form of
// definite clauses, grouped into a list of positive literals (facts) and
// a list of Horn clause implications (rules). The inference procedure
// maintains a list of goals. On each step, a proof is sought for the
// first goal in this list, starting by an attempt to unify the goal with
// any known fact in the knowledge base. If this fails, the rules are
// examined in the order in which they appear in the knowledge base, searching
// for a consequent that unifies with the goal. Upon successful unification,
// a proof is sought for the conjunction of the rule antecedents. If this
// fails, further rules are considered. Note that this is a strictly
// depth-first approach, so it is incomplete. Note, also, that there is
// no backtracking with regard to matches to facts -- the first match is
// always taken and other potential matches are ignored. This can make
// the algorithm incomplete in another way. In short, the order in which
// facts and rules appear in the knowledge base can have a large influence
// on the behavior of this inference procedure.
//
// In order to use this inference engine, the knowledge base must be
// initialized by a call to "initKB". Queries are then submitted using the
// "ask" method. The "ask" function returns a binding list which includes
// bindings for intermediate variables.
//
// David Noelle -- Tue Oct  9 18:48:57 PDT 2018
//                 Modified Mon Oct 12 17:18:55 PDT 2020
//


import java.util.*;


public class BackwardChain {

    public KnowledgeBase kb;

	// Default constructor ...
	public BackwardChain() {
		this.kb = new KnowledgeBase();
	}

	// initKB -- Initialize the knowledge base by interactively requesting
	// file names and reading those files. Return false on error.
	public boolean initKB() {
		return (kb.readKB());
	}


	
	
	
	// START STUDENT SOLUTION CODE SECTION
	// Do not modify this comment or anything above it, with the
	// exception of the file header comment.

	// unifyCC -- Return the most general unifier for the two provided
	// constants, or null if no unification is possible. The argument
	// binding list may be assumed to have been freshly allocated.
	BindingList unifyCC(Term t1, Term t2, BindingList bl) {

	    // PLACE STUDENT SOLUTION CODE HERE

		BindingList newBL = new BindingList(bl);
		if !(t1.isConstant().equals(t2.isConstant())){
			return (null);
		}
		return (unifyCC(t1, t2, bl));
		
		

	}
	
	// unifyCV -- Return the most general unifier for the two provided
	// terms, a constant and a variable, or null if no unification is
	// possible. The argument binding list may be assumed to have been
	// freshly allocated.
	BindingList unifyCV(Term t1, Term t2, BindingList bl) {

	    // PLACE STUDENT SOLUTION CODE HERE
		
		BindingList newBL = new BindingList(bl);
		if !(t1.isConstant().equals(t2.isVariable())){
			return (null);
		}
		return (unifyCV(t1, t2, bl));

	}
	
	// unifyCF -- Return the most general unifier for the two provided
	// terms, a constant and a function, or null if no unification is
	// possible. The argument binding list may be assumed to have been
	// freshly allocated.
	BindingList unifyCF(Term t1, Term t2, BindingList bl) {

	    // PLACE STUDENT SOLUTION CODE HERE
		
		BindingList newBL = new BindingList(bl);
		if !(t1.isConstant().equals(t2.isFunction())){
			return (null);
		}
		return (unifyCF(t1, t2, bl));


	}

	// unifyVC -- Return the most general unifier for the two provided
	// terms, a variable and a constant, or null if no unification is
	// possible. The argument binding list may be assumed to have been
	// freshly allocated.
	BindingList unifyVC(Term t1, Term t2, BindingList bl) {

	    // PLACE STUDENT SOLUTION CODE HERE
		
		BindingList newBL = new BindingList(bl);
		if !(t1.isVariable().equals(t2.isConstant())){
			return (null);
		}
		return (unifyVC(t1, t2, bl));


	}
	
	// unifyVV -- Return the most general unifier for the two provided
	// variables, or null if no unification is possible. The argument
	// binding list may be assumed to have been freshly allocated.
	BindingList unifyVV(Term t1, Term t2, BindingList bl) {

	    // PLACE STUDENT SOLUTION CODE HERE
		
		BindingList newBL = new BindingList(bl);
		if !(t1.isVariable().equals(t2.isVariable())){
			return (null);
		}
		return (unifyVV(t1, t2, bl));


	}
	
	// unifyVF -- Return the most general unifier for the two provided
	// terms, a variable and a function, or null if no unification is
	// possible. The argument binding list may be assumed to have been
	// freshly allocated.
	BindingList unifyVF(Term t1, Term t2, BindingList bl) {

	    // PLACE STUDENT SOLUTION CODE HERE
		
		BindingList newBL = new BindingList(bl);
		if !(t1.isVariable().equals(t2.isFunction())){
			return (null);
		}
		return (unifyVF(t1, t2, bl));


	}
	
	// unifyFC -- Return the most general unifier for the two provided
	// terms, a function and a constant, or null if no unification is
	// possible. The argument binding list may be assumed to have been
	// freshly allocated.
	BindingList unifyFC(Term t1, Term t2, BindingList bl) {

	    // PLACE STUDENT SOLUTION CODE HERE
		
		BindingList newBL = new BindingList(bl);
		if !(t1.isFunction().equals(t2.isConstant())){
			return (null);
		}
		return (unifyFC(t1, t2, bl));


	}
	
	// unifyFV -- Return the most general unifier for the two provided
	// terms, a function and a variable, or null if no unification is
	// possible. The argument binding list may be assumed to have been
	// freshly allocated.
	BindingList unifyFV(Term t1, Term t2, BindingList bl) {

	    // PLACE STUDENT SOLUTION CODE HERE
		
		BindingList newBL = new BindingList(bl);
		if !(t1.isFunction().equals(t2.isVariable())){
			return (null);
		}
		return (unifyFV(t1, t2, bl));


	}
	
	// unifyFF -- Return the most general unifier for the two provided
	// terms, both functions, or null if no unification is possible.
	// The argument binding list may be assumed to have been freshly
	// allocated.
	BindingList unifyFF(Term t1, Term t2, BindingList bl) {

	    // PLACE STUDENT SOLUTION CODE HERE
		
		BindingList newBL = new BindingList(bl);
		if !(t1.isFunction().equals(t2.isFunction())){
			return (null);
		}
		return (unifyFF(t1, t2, bl));
		
	}

	// END STUDENT SOLUTION CODE SECTION
	// Do not modify this comment or anything below it.
	
	
	
	
	// unify -- Return the most general unifier for the two provided terms,
	// or null if no unification is possible. The returned binding list
	// should be freshly allocated.
	
	
	
	
	
	
	
	
	public BindingList unify(Term t1, Term t2, BindingList bl) {
		BindingList newBL = new BindingList(bl);
		if (t1.isConstant()) {
			if (t2.isConstant()) {
				return (unifyCC(t1, t2, newBL));
			}
			if (t2.isVariable()) {
				return (unifyCV(t1, t2, newBL));
			}
			if (t2.isFunction()) {
				return (unifyCF(t1, t2, newBL));
			}
		}
		if (t1.isVariable()) {
			if (t2.isConstant()) {
				return (unifyVC(t1, t2, newBL));
			}
			if (t2.isVariable()) {
				return (unifyVV(t1, t2, newBL));
			}
			if (t2.isFunction()) {
				return (unifyVF(t1, t2, newBL));
			}
		}
		if (t1.isFunction()) {
			if (t2.isConstant()) {
				return (unifyFC(t1, t2, newBL));
			}
			if (t2.isVariable()) {
				return (unifyFV(t1, t2, newBL));
			}
			if (t2.isFunction()) {
				return (unifyFF(t1, t2, newBL));
			}
		}
		// We should never get here, but ...
		return (null);
	}

	// unify -- Return the most general unifier for the two provided literals,
	// or null if no unification is possible. The returned binding list
	// should be freshly allocated.
	
	public BindingList unify(Literal lit1, Literal lit2, BindingList bl) {
		if (!(lit1.pred.equals(lit2.pred))) {
			// Different predicates, so no match ...
			return (null);
		}
		// The literals match if the arguments match ...
		return (unify(lit1.args, lit2.args, bl));
	}
		
	// unify -- Return the most general unifier for the two provided lists of
	// terms, or null if no unification is possible. The returned binding list
	// should be freshly allocated.
	
	
	public BindingList unify(Function f1, Function f2, BindingList bl) {
		if (!(f1.func.equals(f2.func))) {
			// Different function names, so no match ...
			return (null);
		}
		// The terms match if the arguments match ...
		return (unify(f1.args, f2.args, bl));
	}

	// unify -- Return the most general unifier for the two provided lists of
	// terms, or null if no unification is possible. The returned binding
	// list should be freshly allocated.
	public BindingList unify(List<Term> ts1, List<Term> ts2, BindingList bl) {
		if (bl == null)
			return (null);
		if ((ts1.size() == 0) && (ts2.size() == 0))
			// Empty lists match other empty lists ...
			return (new BindingList(bl));
		if ((ts1.size() == 0) || (ts2.size() == 0))
			// Ran out of arguments in one list before reaching the
			// end of the other list, which means the two argument lists
			// can't match ...
			return (null);
		List<Term> terms1 = new LinkedList<Term>();
		List<Term> terms2 = new LinkedList<Term>();
		terms1.addAll(ts1);
		terms2.addAll(ts2);
		Term t1 = terms1.get(0);
		Term t2 = terms2.get(0);
		terms1.remove(0);
		terms2.remove(0);
		return (unify(terms1, terms2, unify(t1, t2, bl)));
	}

	// askFacts -- Examine all of the facts in the knowledge base to
	// determine if any of them unify with the given literal, under the
	// given binding list. If a unification is found, return the
	// corresponding most general unifier. If none is found, return null
	// to indicate failure.
	BindingList askFacts(Literal lit, BindingList bl) {
		BindingList mgu = null; // Most General Unifier
		for (Literal fact : kb.facts) {
			mgu = unify(lit, fact, bl);
			if (mgu != null)
				return (mgu);
		}
		return (null);
	}

	// askFacts -- Examine all of the facts in the knowledge base to
	// determine if any of them unify with the given literal. If a
	// unification is found, return the corresponding most general unifier.
	// If none is found, return null to indicate failure.
	BindingList askFacts(Literal lit) {
		return (askFacts(lit, new BindingList()));
	}

	// ask -- Try to prove the given goal literal, under the constraints of
	// the given binding list, using both the list of known facts and the
	// collection of known rules. Terminate as soon as a proof is found,
	// returning the resulting binding list for that proof. Return null if
	// no proof can be found. The returned binding list should be freshly
	// allocated.
	BindingList ask(Literal goal, BindingList bl) {
		BindingList result = askFacts(goal, bl);
		if (result != null) {
			// The literal can be unified with a known fact ...
			return (result);
		}
		// Need to look at rules ...
		for (Rule candidateRule : kb.rules) {
			if (candidateRule.consequent.pred.equals(goal.pred)) {
				// The rule head uses the same predicate as the goal ...
				// Standardize apart ...
				Rule r = candidateRule.standardizeApart();
				// Check to see if the consequent unifies with the goal ...
				result = unify(goal, r.consequent, bl);
				if (result != null) {
					// This rule might be part of a proof, if we can prove
					// the rule's antecedents ...
					result = ask(r.antecedents, result);
					if (result != null) {
						// The antecedents have been proven, so the goal
						// is proven ...
						return (result);
					}
				}
			}
		}
		// No rule that matches has antecedents that can be proven. Thus,
		// the search fails ...
		return (null);
	}

	// ask -- Try to prove the given goal literal using both the list of
	// known facts and the collection of known rules. Terminate as soon as
	// a proof is found, returning the resulting binding list for that proof.
	// Return null if no proof can be found. The returned binding list
	// should be freshly allocated.
	BindingList ask(Literal goal) {
		return (ask(goal, new BindingList()));
	}

	// ask -- Try to prove the given list of goal literals, under the
	// constraints of the given binding list, using both the list of known
	// facts and the collection of known rules. Terminate as soon as a proof
	// is found, returning the resulting binding list for that proof. Return
	// null if no proof can be found. The returned binding list should be
	// freshly allocated.
	BindingList ask(List<Literal> goals, BindingList bl) {
		if (goals.size() == 0) {
			// All goals have been satisfied ...
			return (bl);
		} else {
			List<Literal> newGoals = new LinkedList<Literal>();
			newGoals.addAll(goals);
			Literal goal = newGoals.get(0);
			newGoals.remove(0);
			BindingList firstBL = ask(goal, bl);
			if (firstBL == null) {
				// Failure to prove one of the goals ...
				return (null);
			} else {
				// Try to prove the remaining goals ...
				return (ask(newGoals, firstBL));
			}
		}
	}

}

