//'''''''''''''''''' Traverse through the HashMap'''''''''''''''''''''''''''''''''''''''''''''''''



HashMap<Integer, Integer> map = new HashMap<>();

//------------- way 1 ------------------------ using For Each Loop````````````````````````````````
for( Map.Entry<String, Integer> ele : map.entrySet() ){
		    String key = ele.getKey();
		    int value = ele.getValue();
		    
		    System.out.println(key + " -> " + value);
}


// ------------------way 2 ````````````````````````````Using Iterator object`````````````````````````````

		Iterator itr = map.entrySet().iterator();
		
		while( itr.hasNext() ){
		    Map.Entry ele = (Map.Entry)itr.next();
		    
		    String key = (String)ele.getKey();
		    int value = (int)ele.getValue();
		    
		    System.out.println(key + " -> " + value);
		}

// -------WAY 3-------------- ONE LINE ITERATOR`````````````````````````````````````` Best For Printing````````````````````

map.forEach((k,v) -> System.out.println(k + " -> " + v)  );






// `````````````````````Sorted Order Set``````````````````````````````````
// If we want the Set in sorted order then use the TreeSet
        // work on String, Integer
    // Iterate thrugh Direct For Each Loop

TreeSet<String> set = new TreeSet<>();
for(String str : set){
		    System.out.println(str);
}


// `````````````````` WANT TO ACCESS THE HASHMAP IN SAME ORDER AS WE HAVE INSERTED WITH KEY PAIR``````````````````````````````
	// use the Linked hashmap 
	Map<Integer, String> map = new LinkedHashMap<>();





// `````````````````` SORT IN TERMS OF KEY `````````````````````````````````````````````````````````````
	
	Map<Integer, String> map = new TreeMap<>();
		
		map.put(215 , "Himanshu");
		map.put(1215 , "Ajay");
		map.put(8215 , "Vikram");
		map.put(524, "Vikash");
		map.put(9972 , "zzWks");
		map.put(379, "Manshu");
		map.put(975, "Kisdd");
		map.put(2, "Himanshu");
		map.put(789, "Bablu");
		
	    for(Map.Entry<Integer, String> ele : map.entrySet() ){
            	int key = ele.getKey();
            	String val = ele.getValue();
            
            	System.out.println(key + " -> " + val);
        }




// ------------ SORTED IN TERMS OF KEYS`````````````````````````````````````````````````````````````````````````````````

Set<Map.Entry<Integer,String>> entry = map.entrySet();
List<Map.Entry<Integer,String>> list = new ArrayList(entry);
		
		Collections.sort(list, new Comparator<Map.Entry<Integer,String>>(){
		    
		    @Override
		    public int compare(Map.Entry<Integer, String> o1, Map.Entry<Integer, String> o2){
		        return o1.getValue().compareTo(o2.getValue());
		    }
		} );
		
	// Accessing from the List

	list.forEach(s -> System.out.println(s.getKey() + " -> " + s.getValue() )  );



//``````````````````````````````````````````````````````````````````NEW WAY````````````````````````````````

		
		
// coping into the new LinkedHashMap from the list

Map<Integer, String> sortedMap = new LinkedHashMap<>();
list.forEach(s -> sortedMap.put(s.getKey() , s.getValue())  );

// print
sortedMap.forEach( (k,v) -> System.out.println(k + " -> " + v)  );




// ++++++++++++ SORT IN Terms of Value in REVERSE Order ++++++++++++++++++++++++++++++++
       // Convert the HashMap to a List of Map entries
        List<Map.Entry<Integer, Double>> list = new ArrayList<>(tmap.entrySet());

        // Sort the list based on values in descending order
        Collections.sort(list, new Comparator<Map.Entry<Integer, Double>>() {
            public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2) {
                return o2.getValue().compareTo(o1.getValue());  // Sort in reverse order
            }
        });
        
        // Maintain insertion order in a LinkedHashMap
        Map<Integer, Double> map = new LinkedHashMap<>();
        for (Map.Entry<Integer, Double> entry : list) {
            map.put(entry.getKey(), entry.getValue());
        }




// `````````````````````````````````````````````````````PRIORITY QUEUE````````````````````````````````````````````

Queue<Integer> que = new PriorityQueue<>();  // Min Heap.

Queue<Integer> que = new PriorityQueue<>( Collections.reverseOrder() );  // MAX Heap



// ```````````````````````````````  DEQUEU ``````````````````````````` Can be inseted from front and back.


 Deque<Integer> dq = new ArrayDeque<>();
// offer  -> it is used to insert in the end.
// getFirst()   ->
// getLast()
// addFirst()
// addLast()



