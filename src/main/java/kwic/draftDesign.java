

/* # note1
            - Diff channels for titles from diff Help Manual

*/

        /*
            scrap  - mn <- Raw titles <- filenames E.g. Chinese / Life Science / Computer Science (inside pipe filter)

        */




        /*

        Pipeline {

        static String[][][] generateConcordance (input) {
                        // need input

                        pf := construct pipe chain
                        // will output
                        ((((Transformer.LEXI((Transformer.SHIFT(Adapter.ITERATE_TITLES(READ(pathTitle)))))))));

                        return pf(input)
                        }
                    }

        static ArrayList<ArrayList<String>> selectByFirstWord (word,concordance) {


                         pf :=       ((((SELECTOR.REQUIRE)));

                      selected :=   concordance.filter(title -> pf(title))

        }



        Manual {
            string _filename;

            string[][] concordance;
            TreeSetWithDuplicaate(<string[]>) results;

         */
/*
            record(result) {
                results.append(result)
            }


            result n
            construct(filename){

                _filename = filename
                name = parse(_filename)
            }

            transformNativeRawData() //pipe & filter{
                pf = Pipeline::PipeAndFilter1;
                concordance <- (pf)_filename
            }
        }

        Librarian {
            TreeMap<Manual> manuals;

            search (query){
                    for(m : manuals) {
                        _concordance = m.getCon()
                        result := selectByFirstWord(query,_concordance);
                        m.record(result)
                    }

                }
            format() {
                                for(m : manuals) {

                                m_name = manual.getName()
                                history = manual.getHistory(false)
                                String historyString;
                                for(h : history){

                                historyString += h.toString() + line.Sep
                                }

                                return m_name.toString() +line.Sep + historyString
}

            }
        }
        // Search 1 m1
        /*

            m1
            r1.s1
            r1.s2
        /*
        // Search 2 m1
        /*

            m1
            r2.s1
            [r1.s1
            r1.s2]
        /*
        // Search 3 m1
        /*

            m1
            []       // r3.length == 0
            [r2.s1]
            [r1.s1
            r1.s2]
        /*


        //
        // TODO

        // who should store output??
        // who should format output??

        // END OF CLASSES


        // MAIN
        1. Get titles and process/store required data
        * see # note1
        String args[0] = scanner in
        Process list of the txt files E.g. CourseTitles / MovieTitles


         lib := new Lib();

         */


        /* 2. Get search input and display output
            while(true){
                if(input <- cin  != KWQ){ // search
                    lib.search(input)
                    output = lib.formatHistory()
                    display(output)
                }else{
                display("execution ends")
                break;
                }
            }
        */