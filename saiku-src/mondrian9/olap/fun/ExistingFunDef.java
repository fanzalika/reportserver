/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (c) 2014-2017 Hitachi Vantara
// All rights reserved.
*/
package mondrian9.olap.fun;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mondrian9.calc.Calc;
import mondrian9.calc.ExpCompiler;
import mondrian9.calc.IterCalc;
import mondrian9.calc.TupleCollections;
import mondrian9.calc.TupleIterable;
import mondrian9.calc.TupleList;
import mondrian9.calc.impl.AbstractListCalc;
import mondrian9.mdx.ResolvedFunCall;
import mondrian9.olap.Evaluator;
import mondrian9.olap.Exp;
import mondrian9.olap.Hierarchy;
import mondrian9.olap.Member;
import mondrian9.olap.Validator;
import mondrian9.olap.type.Type;

/**
 * Existing keyword limits a set to what exists within the current context, ie
 * as if context members of the same dimension as the set were in the slicer.
 */
public class ExistingFunDef extends FunDefBase {

    static final ExistingFunDef instance = new ExistingFunDef();

    protected ExistingFunDef() {
      super(
          "Existing",
          "Existing <Set>",
          "Forces the set to be evaluated within the current context.",
          "Pxx");
    }

    public Type getResultType(Validator validator, Exp[] args) {
        return args[0].getType();
    }

    public Calc compileCall(ResolvedFunCall call, ExpCompiler compiler) {
        final IterCalc setArg = compiler.compileIter(call.getArg(0));
        final Type myType = call.getArg(0).getType();

        return new AbstractListCalc(call, new Calc[] {setArg}) {
            public boolean dependsOn(Hierarchy hierarchy) {
                return myType.usesHierarchy(hierarchy, false);
            }

            public TupleList evaluateList(Evaluator evaluator) {
                TupleIterable setTuples = setArg.evaluateIterable(evaluator);

                TupleList result =
                    TupleCollections.createList(setTuples.getArity());
                List<Member> contextMembers =
                    Arrays.asList(evaluator.getMembers());

                List<Hierarchy> argDims = null;
                List<Hierarchy> contextDims = getHierarchies(contextMembers);

                for (List<Member> tuple : setTuples) {
                    if (argDims == null) {
                        argDims = getHierarchies(tuple);
                    }
                    if (existsInTuple(tuple, contextMembers,
                        argDims, contextDims, evaluator))
                    {
                        result.add(tuple);
                    }
                }
                return result;
            }
        };
    }

    private static List<Hierarchy> getHierarchies(final List<Member> members) {
        List<Hierarchy> hierarchies = new ArrayList<Hierarchy>(members.size());
        for (Member member : members) {
            hierarchies.add(member.getHierarchy());
        }
        return hierarchies;
    }

}
// End ExistingFunDef.java