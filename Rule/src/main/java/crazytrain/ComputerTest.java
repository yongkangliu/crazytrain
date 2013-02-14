package crazytrain;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.QueryResults;
import org.drools.runtime.rule.QueryResultsRow;

public class ComputerTest {

    /**
     * Create KnowledgeBase object.
     */
    private static KnowledgeBase readKnowledgeBase() throws Exception {
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource("ComputerRules.drl"), ResourceType.DRL);
        KnowledgeBuilderErrors errors = kbuilder.getErrors();
        if (errors.size() > 0) {
            for (KnowledgeBuilderError error : errors) {
                System.err.println(error);
            }
            throw new IllegalArgumentException("Could not parse knowledge.");
        }
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
        return kbase;
    }

    /**
     * Create the computer object.
     */
    private static Computer createComputer(String modelName, int memory, int cpuSpeed, int videoCard, int price) {
        Computer computer = new Computer();
        computer.setModelName(modelName);
        computer.setMemory(memory);
        computer.setCpuSpeed(cpuSpeed);
        computer.setVideoCard(videoCard);
        computer.setPrice(price);
        return computer;
    }

    public static void main(String[] args) {
        try {
            // load up the knowledge base
            KnowledgeBase kbase = readKnowledgeBase();

            StatefulKnowledgeSession statefulSession = kbase.newStatefulKnowledgeSession();

            statefulSession.insert(createComputer("1: DIY computer", 2, 5, 0, 800));
            statefulSession.insert(createComputer("2: Sony laptop", 8, 3, 1, 1500));
            statefulSession.insert(createComputer("3: HTC Phone", 1, 1, 0, 400));
            statefulSession.insert(createComputer("4: HP Game Server", 16, 4, 2, 3000));
            statefulSession.insert(createComputer("5: Google Server", 32, 5, 0, 8000));

            ComputerAgendaFilter filter = new ComputerAgendaFilter();
            statefulSession.fireAllRules(filter);

            // Answer question: What are my options for a safe gaming computer?
            System.out.println("----Start----");
            System.out.println("What are my options for a safe gaming computer?");
            QueryResults queryResults = statefulSession.getQueryResults("query safe");
            if (queryResults.size() > 0) {
                System.out.println("There are " + queryResults.size() + " safe gaming computers:");
                for (QueryResultsRow results : queryResults) {
                    Computer computer = (Computer) results.get("computer");
                    System.out.println("    " + computer.getModelName());
                }
            } else {
                System.out.println("There isn't a safe gaming computer.");
            }

            // Answer question: Are there any number crunchers that are risky?
            System.out.println("\nAre there any number crunchers that are risky?");
            queryResults = statefulSession.getQueryResults("query risky");
            if (queryResults.size() > 0) {
                System.out.println("Yes, there are " + queryResults.size() + " number crunchers that are risky.");
                for (QueryResultsRow results : queryResults) {
                    Computer computer = (Computer) results.get("computer");
                    System.out.println("    " + computer.getModelName());
                }
            } else {
                System.out.println("No, there isn't number cruncher that is risky.");
            }

            // Answer question: Are all gaming computers hot?
            System.out.println("\nAre all gaming computers hot?");
            queryResults = statefulSession.getQueryResults("query hot");
            if (queryResults.size() > 0) {
                System.out.println("No, there are " + queryResults.size() + " gaming computers aren't hot");
                for (QueryResultsRow results : queryResults) {
                    Computer computer = (Computer) results.get("computer");
                    System.out.println("    " + computer.getModelName());
                }
            } else {
                System.out.println("Yes, all gaming computers are hot");
            }
            statefulSession.dispose();

            // Analyze the sixth computer
            statefulSession = kbase.newStatefulKnowledgeSession();
            Computer computer = createComputer("6: Apple Mac", 2, 2, 2, 5500);
            statefulSession.insert(computer);
            statefulSession.fireAllRules(filter);
            System.out.println("\nThe " + computer.getModelName() + " is " + computer.rulesOpinion());
            statefulSession.dispose();

            System.out.println("----End----");
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
