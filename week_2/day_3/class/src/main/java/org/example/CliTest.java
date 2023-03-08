package org.example;

import org.apache.commons.cli.*;

public class CliTest {
    public static void main(String[] args) {
        Options options = new Options();
        options.addOption("h","help",false,"print this message");

        Option d = Option.builder("D")
                .argName("property=value")
                .hasArgs()
                .valueSeparator('=')
                .desc("use value for given property")
                .build();
        options.addOption(d);
        Option buildfile = Option.builder("buildfile")
                .argName("file")
                .hasArg()
                .desc("use given buildfile")
                .build();
        options.addOption(buildfile);
        Option debug = Option.
                builder("debug")
                .desc("print debugging information")
                .build();
        options.addOption(debug);
        Option emacs = Option.
                builder("emacs")
                .desc("produce logging information without adornments")
                .build();
        options.addOption(emacs);
        Option file = Option.
                builder("file")
                .hasArg()
                .argName("file")
                .desc("search for buildfile towards the root of the\n" +
                        "                        filesystem and use it")
                .build();
        options.addOption(file);
        Option listener = Option.
                builder("listener")
                .hasArg()
                .argName("classname")
                .desc("add an instance of class as a project listener")
                .build();
        options.addOption(listener);
        Option logger = Option.
                builder("logger")
                .hasArg()
                .argName("classname")
                .desc("the class which it to perform logging")
                .build();
        options.addOption(logger);
        Option projecthelp = Option.
                builder("projecthelp")
                .desc("print project help information")
                .build();
        options.addOption(projecthelp);

        Option quiet = Option.
                builder("quiet")
                .desc("be extra quiet")
                .build();
        options.addOption(quiet);

        Option verbose = Option.
                builder("verbose")
                .desc("be extra verbose")
                .build();
        options.addOption(verbose);


        Option version = Option.
                builder("version")
                .desc("print the version information and exit")
                .build();
        options.addOption(version);
        CommandLineParser parser = new DefaultParser();


        try {
            CommandLine cmd = parser.parse(options, args);
            for (Option option : cmd.getOptions()) {
                if(option.getOpt().equals("h")){
                    HelpFormatter formatter = new HelpFormatter();
                    formatter.printHelp("ant [option] \nOptions:",options);
                    System.out.println("도움말을 요청하고");
                }
                if(option.getOpt().equals("logfile")){
                    System.out.println(cmd.getOptionValue("logfile"));
                }
                if(option.getOpt().equals("D")){
                    System.out.println(cmd.getOptionValue("D") + "을 등록합니다.");
                }
                if(option.getOpt().equals("listener")){
                    System.out.println(cmd.getOptionValue("listener"));
                }

            }
        } catch (ParseException ignore) {
            System.err.println("명령어 인수가 잘못되었습니다.");
            System.out.println(ignore);
        }


    }
}
