//Maya ASCII 2020 scene
//Name: crystal_matrix.ma
//Last modified: Mon, Jul 19, 2021 09:28:20 AM
//Codeset: 1252
requires maya "2020";
currentUnit -l centimeter -a degree -t film;
fileInfo "application" "maya";
fileInfo "product" "Maya 2020";
fileInfo "version" "2020";
fileInfo "cutIdentifier" "201911140446-42a737a01c";
fileInfo "osv" "Microsoft Windows 10 Technical Preview  (Build 19042)\n";
fileInfo "UUID" "9064E522-41AD-E012-D4C7-FBBCF274D027";
createNode transform -s -n "persp";
	rename -uid "D9807981-4B22-C606-D4D0-9BB3AED3458D";
	setAttr ".v" no;
	setAttr ".t" -type "double3" 1.611506821691163 1.512066501035684 -1.5276470014905026 ;
	setAttr ".r" -type "double3" -33.938352723492756 -949.39999999999077 0 ;
createNode camera -s -n "perspShape" -p "persp";
	rename -uid "C68E59DC-4A83-BC0C-7B68-11A23937F3B1";
	setAttr -k off ".v" no;
	setAttr ".fl" 34.999999999999993;
	setAttr ".coi" 2.3371486276353277;
	setAttr ".imn" -type "string" "persp";
	setAttr ".den" -type "string" "persp_depth";
	setAttr ".man" -type "string" "persp_mask";
	setAttr ".hc" -type "string" "viewSet -p %camera";
createNode transform -s -n "top";
	rename -uid "52F1CF60-401F-AA57-6750-759E56E9DC1F";
	setAttr ".v" no;
	setAttr ".t" -type "double3" -0.70850968487124022 1000.1 -0.48430682405513192 ;
	setAttr ".r" -type "double3" -90 0 0 ;
createNode camera -s -n "topShape" -p "top";
	rename -uid "CD990E1C-4366-F080-B4FC-9A8456A7BDB4";
	setAttr -k off ".v" no;
	setAttr ".rnd" no;
	setAttr ".coi" 1000.1;
	setAttr ".ow" 1.7467724724044797;
	setAttr ".imn" -type "string" "top";
	setAttr ".den" -type "string" "top_depth";
	setAttr ".man" -type "string" "top_mask";
	setAttr ".hc" -type "string" "viewSet -t %camera";
	setAttr ".o" yes;
createNode transform -s -n "front";
	rename -uid "947F38A1-4D3A-539A-5749-E6A447E21D58";
	setAttr ".v" no;
	setAttr ".t" -type "double3" -0.51344889739566701 0.54491641986075701 1000.1 ;
createNode camera -s -n "frontShape" -p "front";
	rename -uid "D9D621CA-4048-9E60-AC83-76BB87AC765B";
	setAttr -k off ".v" no;
	setAttr ".rnd" no;
	setAttr ".coi" 1000.1;
	setAttr ".ow" 1.6253744146783899;
	setAttr ".imn" -type "string" "front";
	setAttr ".den" -type "string" "front_depth";
	setAttr ".man" -type "string" "front_mask";
	setAttr ".hc" -type "string" "viewSet -f %camera";
	setAttr ".o" yes;
createNode transform -s -n "side";
	rename -uid "8F658580-4F7C-6B0A-1C78-BCA6697FF562";
	setAttr ".v" no;
	setAttr ".t" -type "double3" 1000.1 0.13400895882969344 -0.4741703366974811 ;
	setAttr ".r" -type "double3" 0 90 0 ;
createNode camera -s -n "sideShape" -p "side";
	rename -uid "6F66AB72-478F-0D5A-E5E9-4D86123670AF";
	setAttr -k off ".v" no;
	setAttr ".rnd" no;
	setAttr ".coi" 1000.1;
	setAttr ".ow" 1.6124139271784939;
	setAttr ".imn" -type "string" "side";
	setAttr ".den" -type "string" "side_depth";
	setAttr ".man" -type "string" "side_mask";
	setAttr ".hc" -type "string" "viewSet -s %camera";
	setAttr ".o" yes;
createNode transform -n "crystal";
	rename -uid "E7CFA192-4D5A-48AF-65A9-20B2087B1AD4";
	setAttr ".sp" -type "double3" -1.1102230246251565e-16 5.5511151231257827e-16 2.2204460492503131e-16 ;
createNode mesh -n "crystalShape" -p "crystal";
	rename -uid "75F6A59C-4E6E-5BCB-1299-2A974046E7AC";
	setAttr -k off ".v";
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".pv" -type "double2" 0.50152753479778767 0.49749467894434929 ;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr -s 24 ".uvst[0].uvsp[0:23]" -type "float2" 0.99539077 0.0036315573
		 0.99539059 0.0036315573 0.0076642968 0.0036315583 0.0076646507 0.99135792 0.0076642968
		 0.0036319122 0.99539059 0.99135792 0.0076642968 0.0036311969 0.99539059 0.99135756
		 0.0076642968 0.99135816 0.99539059 0.99135816 0.99539042 0.0036315573 0.99539042
		 0.99135792 0.007664355 0.0036315573 0.007664355 0.99135792 0.99539059 0.0036311969
		 0.0076646507 0.0036315573 0.0076642968 0.99135756 0.0076642968 0.0036315573 0.99539059
		 0.0036319122 0.0076642968 0.99135792 0.99539059 0.99135792 0.99539059 0.0036315583
		 0.0076642968 0.99135792 0.99539077 0.99135792;
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr -s 8 ".vt[0:7]"  0.11880073 -0.13079429 0.14968091 0.0031710565 -0.2840701 -0.032723248
		 0.17858538 0.14101729 -0.006463021 0.073271215 -0.056712002 -0.14596489 0.017374068 0.29420814 0.025498837
		 -0.10177618 0.12792021 -0.12391835 -0.10736704 0.0033093691 0.17712867 -0.1852687 -0.14372486 -0.017772317;
	setAttr -s 12 ".ed[0:11]"  0 1 0 2 3 0 4 5 0 6 7 0 0 2 0 1 3 0 2 4 0
		 3 5 0 4 6 0 5 7 0 6 0 0 7 1 0;
	setAttr -s 6 -ch 24 ".fc[0:5]" -type "polyFaces" 
		f 4 0 5 -2 -5
		mu 0 4 17 1 20 22
		f 4 1 7 -3 -7
		mu 0 4 2 21 5 19
		f 4 2 9 -4 -9
		mu 0 4 4 18 7 16
		f 4 3 11 -1 -11
		mu 0 4 6 14 9 8
		f 4 -12 -10 -8 -6
		mu 0 4 15 10 11 3
		f 4 10 4 6 8
		mu 0 4 12 0 23 13;
	setAttr ".cd" -type "dataPolyComponent" Index_Data Edge 0 ;
	setAttr ".cvd" -type "dataPolyComponent" Index_Data Vertex 0 ;
	setAttr ".pd[0]" -type "dataPolyComponent" Index_Data UV 0 ;
	setAttr ".hfd" -type "dataPolyComponent" Index_Data Face 0 ;
createNode transform -n "ring02";
	rename -uid "984477AD-48EA-D9F2-F104-79BD24BE7EBC";
createNode mesh -n "ringShape2" -p "ring02";
	rename -uid "3662CCA9-465C-6731-F7C2-CC932617B02E";
	setAttr -k off ".v";
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".pv" -type "double2" 0.5 0.56249371916055679 ;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr -s 45 ".uvst[0].uvsp[0:44]" -type "float2" 0 0.62498748 1 0.37501252
		 0.125 0.62498748 0.25 0.62498748 0.375 0.62498748 0.5 0.62498748 0.625 0.62498748
		 0.75 0.62498748 0.875 0.62498748 1 0.62498748 0 0.37501252 0.125 0.37501252 0.25
		 0.37501252 0.375 0.37501252 0.5 0.37501252 0.625 0.37501246 0.75 0.37501255 0.875
		 0.37501249 1 0.87501252 1 1 0 1 0.125 1 0.25 1 0.375 1 0.5 1 0.625 1 0.75 1 0.875
		 1 2.4934157e-09 0.87501252 0.125 0.87501258 0.25 0.87501252 0.375 0.87501258 0.5
		 0.87501252 0.625 0.87501252 0.75 0.87501258 0.875 0.87501252 1.4269655e-09 0.12498747
		 0.125 0.12498744 0.25 0.12498747 0.375 0.12498746 0.5 0.12498748 0.625 0.1249875
		 0.75 0.12498754 0.875 0.12498754 1 0.12498748;
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr -s 32 ".pt[0:31]" -type "float3"  0.3798511 -0.49705693 0.37921154 
		0.3798511 -0.50172269 0.37921154 0.32964629 -0.49705693 0.50071508 0.32964629 -0.50172269 
		0.50071508 0.38006204 -0.49705693 0.62213123 0.38006204 -0.50172269 0.62213123 0.50156558 
		-0.49705693 0.6723361 0.50156558 -0.50172269 0.6723361 0.62298173 -0.49705693 0.62192035 
		0.62298173 -0.50172269 0.62192035 0.6731866 -0.49705693 0.50041682 0.6731866 -0.50172269 
		0.50041682 0.62277085 -0.49705693 0.3790006 0.62277085 -0.50172269 0.3790006 0.50126725 
		-0.49705693 0.32879576 0.50126725 -0.50172269 0.32879576 0.39569977 -0.497031 0.39503273 
		0.3956998 -0.50174862 0.39503273 0.35204017 -0.497031 0.50069565 0.35204017 -0.50174862 
		0.50069565 0.3958832 -0.497031 0.60628259 0.39588323 -0.50174862 0.60628259 0.50154614 
		-0.497031 0.64994216 0.50154614 -0.50174862 0.64994216 0.60713309 -0.497031 0.60609913 
		0.60713309 -0.50174862 0.60609913 0.65079266 -0.497031 0.50043625 0.65079266 -0.50174862 
		0.50043625 0.60694963 -0.497031 0.3948493 0.60694963 -0.50174862 0.3948493 0.50128675 
		-0.497031 0.35118967 0.50128675 -0.50174862 0.35118967;
	setAttr -s 32 ".vt[0:31]"  -0.20808095 0.52783847 -0.79390144 -0.20808095 0.4709411 -0.79390144
		 -0.50141644 0.52783847 -0.91540492 -0.50141644 0.4709411 -0.91540492 -0.79475194 0.52783847 -0.79390144
		 -0.79475194 0.4709411 -0.79390144 -0.91625547 0.52783847 -0.50056595 -0.91625547 0.4709411 -0.50056595
		 -0.79475194 0.52783847 -0.20723045 -0.79475194 0.4709411 -0.20723045 -0.50141644 0.52783847 -0.085726917
		 -0.50141644 0.4709411 -0.085726947 -0.20808092 0.52783847 -0.20723042 -0.20808095 0.4709411 -0.20723045
		 -0.086577386 0.52783847 -0.50056595 -0.086577415 0.4709411 -0.50056595 -0.24632362 0.52815479 -0.75565881
		 -0.24632362 0.47062477 -0.75565875 -0.50141644 0.52815479 -0.86132181 -0.50141644 0.47062477 -0.86132181
		 -0.7565093 0.52815479 -0.75565881 -0.7565093 0.47062477 -0.75565875 -0.86217225 0.52815479 -0.50056595
		 -0.86217225 0.47062477 -0.50056595 -0.7565093 0.52815479 -0.24547312 -0.7565093 0.47062477 -0.24547312
		 -0.50141644 0.52815479 -0.13981006 -0.50141644 0.47062477 -0.13981006 -0.24632359 0.52815479 -0.24547309
		 -0.24632359 0.47062477 -0.24547309 -0.14066052 0.52815479 -0.50056595 -0.14066052 0.47062477 -0.50056595;
	setAttr -s 64 ".ed[0:63]"  0 1 0 1 3 0 3 2 0 2 0 0 0 14 0 14 15 0 15 1 0
		 3 5 0 5 4 0 4 2 0 5 7 0 7 6 0 6 4 0 7 9 0 9 8 0 8 6 0 9 11 0 11 10 0 10 8 0 11 13 0
		 13 12 0 12 10 0 13 15 0 14 12 0 16 17 0 17 31 0 31 30 0 30 16 0 16 18 0 18 19 0 19 17 0
		 18 20 0 20 21 0 21 19 0 20 22 0 22 23 0 23 21 0 22 24 0 24 25 0 25 23 0 24 26 0 26 27 0
		 27 25 0 26 28 0 28 29 0 29 27 0 28 30 0 31 29 0 16 0 0 2 18 0 4 20 0 6 22 0 8 24 0
		 10 26 0 12 28 0 14 30 0 1 17 0 19 3 0 21 5 0 23 7 0 25 9 0 27 11 0 29 13 0 31 15 0;
	setAttr -s 32 -ch 128 ".fc[0:31]" -type "polyFaces" 
		f 4 0 1 2 3
		mu 0 4 0 10 11 2
		f 4 -1 4 5 6
		mu 0 4 1 9 8 17
		f 4 -3 7 8 9
		mu 0 4 2 11 12 3
		f 4 -9 10 11 12
		mu 0 4 3 12 13 4
		f 4 -12 13 14 15
		mu 0 4 4 13 14 5
		f 4 -15 16 17 18
		mu 0 4 5 14 15 6
		f 4 -18 19 20 21
		mu 0 4 6 15 16 7
		f 4 -21 22 -6 23
		mu 0 4 7 16 17 8
		f 4 -2 56 -31 57
		mu 0 4 11 10 36 37
		f 4 -8 -58 -34 58
		mu 0 4 12 11 37 38
		f 4 -11 -59 -37 59
		mu 0 4 13 12 38 39
		f 4 -14 -60 -40 60
		mu 0 4 14 13 39 40
		f 4 -17 -61 -43 61
		mu 0 4 15 14 40 41
		f 4 -20 -62 -46 62
		mu 0 4 16 15 41 42
		f 4 -23 -63 -48 63
		mu 0 4 17 16 42 43
		f 4 -7 -64 -26 -57
		mu 0 4 1 17 43 44
		f 4 24 25 26 27
		mu 0 4 18 19 27 35
		f 4 -25 28 29 30
		mu 0 4 20 28 29 21
		f 4 -30 31 32 33
		mu 0 4 21 29 30 22
		f 4 -33 34 35 36
		mu 0 4 22 30 31 23
		f 4 -36 37 38 39
		mu 0 4 23 31 32 24
		f 4 -39 40 41 42
		mu 0 4 24 32 33 25
		f 4 -42 43 44 45
		mu 0 4 25 33 34 26
		f 4 -45 46 -27 47
		mu 0 4 26 34 35 27
		f 4 49 -29 48 -4
		mu 0 4 2 29 28 0
		f 4 50 -32 -50 -10
		mu 0 4 3 30 29 2
		f 4 51 -35 -51 -13
		mu 0 4 4 31 30 3
		f 4 52 -38 -52 -16
		mu 0 4 5 32 31 4
		f 4 53 -41 -53 -19
		mu 0 4 6 33 32 5
		f 4 54 -44 -54 -22
		mu 0 4 7 34 33 6
		f 4 55 -47 -55 -24
		mu 0 4 8 35 34 7
		f 4 -49 -28 -56 -5
		mu 0 4 9 18 35 8;
	setAttr ".cd" -type "dataPolyComponent" Index_Data Edge 0 ;
	setAttr ".cvd" -type "dataPolyComponent" Index_Data Vertex 0 ;
	setAttr ".pd[0]" -type "dataPolyComponent" Index_Data UV 0 ;
	setAttr ".hfd" -type "dataPolyComponent" Index_Data Face 0 ;
createNode transform -n "ring03";
	rename -uid "A263C1EF-448E-0581-5FA0-798C981E4455";
createNode mesh -n "ringShape3" -p "ring03";
	rename -uid "0633B5F7-4B50-F069-D4E2-A0B45B0509A9";
	setAttr -k off ".v";
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".pv" -type "double2" 0.8125 0.56249376758933067 ;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr -s 45 ".uvst[0].uvsp[0:44]" -type "float2" 0 0.62498748 1 0.37501252
		 0.125 0.62498748 0.25 0.62498748 0.375 0.62498748 0.5 0.62498748 0.625 0.62498748
		 0.75 0.62498748 0.875 0.62498748 1 0.62498748 0 0.37501252 0.125 0.37501252 0.25
		 0.37501252 0.375 0.37501252 0.5 0.37501252 0.625 0.37501246 0.75 0.37501255 0.875
		 0.37501249 1 0.87501252 1 1 0 1 0.125 1 0.25 1 0.375 1 0.5 1 0.625 1 0.75 1 0.875
		 1 2.4934157e-09 0.87501252 0.125 0.87501258 0.25 0.87501252 0.375 0.87501258 0.5
		 0.87501252 0.625 0.87501252 0.75 0.87501258 0.875 0.87501252 1.4269655e-09 0.12498747
		 0.125 0.12498744 0.25 0.12498747 0.375 0.12498746 0.5 0.12498748 0.625 0.1249875
		 0.75 0.12498754 0.875 0.12498754 1 0.12498748;
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr -s 32 ".pt[0:31]" -type "float3"  0.35641387 -0.49707043 0.35581493 
		0.35641387 -0.50170916 0.35581493 0.29652977 -0.49707043 0.50074387 0.29652977 -0.50170916 
		0.50074387 0.35666546 -0.49707043 0.64556855 0.35666546 -0.50170916 0.64556855 0.50159436 
		-0.49707043 0.70545268 0.50159436 -0.50170916 0.70545268 0.64641905 -0.49707043 0.64531696 
		0.64641905 -0.50170916 0.64531696 0.70630318 -0.49707043 0.50038803 0.70630318 -0.50170916 
		0.50038803 0.64616746 -0.49707043 0.35556331 0.64616746 -0.50170916 0.35556334 0.50123858 
		-0.49707043 0.29567918 0.50123858 -0.50170916 0.29567921 0.37300184 -0.49700156 0.37237412 
		0.37300184 -0.50177807 0.37237412 0.31996828 -0.49700156 0.50072348 0.31996828 -0.50177807 
		0.50072348 0.37322465 -0.49700156 0.62898052 0.37322465 -0.50177807 0.62898052 0.50157404 
		-0.49700156 0.68201417 0.50157404 -0.50177807 0.68201417 0.62983108 -0.49700156 0.62875772 
		0.62983108 -0.50177807 0.62875772 0.68286467 -0.49700156 0.50040841 0.68286467 -0.50177807 
		0.50040841 0.62960827 -0.49700156 0.37215132 0.62960827 -0.50177807 0.37215132 0.50125891 
		-0.49700156 0.3191177 0.50125891 -0.50177807 0.3191177;
	setAttr -s 32 ".vt[0:31]"  -0.15152711 0.52767372 -0.85045528 -0.15152711 0.47110584 -0.85045528
		 -0.50141644 0.52767372 -0.9953841 -0.50141644 0.47110584 -0.9953841 -0.85130578 0.52767372 -0.85045528
		 -0.85130578 0.47110584 -0.85045528 -0.99623466 0.52767372 -0.50056595 -0.99623466 0.47110584 -0.50056595
		 -0.85130578 0.52767372 -0.15067661 -0.85130578 0.47110584 -0.15067661 -0.50141644 0.52767372 -0.0057477057
		 -0.50141644 0.47110584 -0.0057477653 -0.15152708 0.52767372 -0.15067658 -0.15152711 0.47110584 -0.15067661
		 -0.0065981746 0.52767372 -0.50056595 -0.0065982044 0.47110584 -0.50056595 -0.19155368 0.52851355 -0.81042874
		 -0.19155368 0.47026604 -0.81042874 -0.50141644 0.52851355 -0.93877816 -0.50141644 0.47026604 -0.93877816
		 -0.81127918 0.52851355 -0.81042874 -0.81127918 0.47026604 -0.81042874 -0.93962872 0.52851355 -0.50056595
		 -0.93962872 0.47026604 -0.50056595 -0.81127918 0.52851355 -0.19070318 -0.81127918 0.47026604 -0.19070318
		 -0.50141644 0.52851355 -0.062353671 -0.50141644 0.47026604 -0.062353671 -0.19155368 0.52851355 -0.19070318
		 -0.19155368 0.47026604 -0.19070318 -0.06320411 0.52851355 -0.50056595 -0.06320411 0.47026604 -0.50056595;
	setAttr -s 64 ".ed[0:63]"  0 1 0 1 3 0 3 2 0 2 0 0 0 14 0 14 15 0 15 1 0
		 3 5 0 5 4 0 4 2 0 5 7 0 7 6 0 6 4 0 7 9 0 9 8 0 8 6 0 9 11 0 11 10 0 10 8 0 11 13 0
		 13 12 0 12 10 0 13 15 0 14 12 0 16 17 0 17 31 0 31 30 0 30 16 0 16 18 0 18 19 0 19 17 0
		 18 20 0 20 21 0 21 19 0 20 22 0 22 23 0 23 21 0 22 24 0 24 25 0 25 23 0 24 26 0 26 27 0
		 27 25 0 26 28 0 28 29 0 29 27 0 28 30 0 31 29 0 16 0 0 2 18 0 4 20 0 6 22 0 8 24 0
		 10 26 0 12 28 0 14 30 0 1 17 0 19 3 0 21 5 0 23 7 0 25 9 0 27 11 0 29 13 0 31 15 0;
	setAttr -s 32 -ch 128 ".fc[0:31]" -type "polyFaces" 
		f 4 0 1 2 3
		mu 0 4 0 10 11 2
		f 4 -1 4 5 6
		mu 0 4 1 9 8 17
		f 4 -3 7 8 9
		mu 0 4 2 11 12 3
		f 4 -9 10 11 12
		mu 0 4 3 12 13 4
		f 4 -12 13 14 15
		mu 0 4 4 13 14 5
		f 4 -15 16 17 18
		mu 0 4 5 14 15 6
		f 4 -18 19 20 21
		mu 0 4 6 15 16 7
		f 4 -21 22 -6 23
		mu 0 4 7 16 17 8
		f 4 -2 56 -31 57
		mu 0 4 11 10 36 37
		f 4 -8 -58 -34 58
		mu 0 4 12 11 37 38
		f 4 -11 -59 -37 59
		mu 0 4 13 12 38 39
		f 4 -14 -60 -40 60
		mu 0 4 14 13 39 40
		f 4 -17 -61 -43 61
		mu 0 4 15 14 40 41
		f 4 -20 -62 -46 62
		mu 0 4 16 15 41 42
		f 4 -23 -63 -48 63
		mu 0 4 17 16 42 43
		f 4 -7 -64 -26 -57
		mu 0 4 1 17 43 44
		f 4 24 25 26 27
		mu 0 4 18 19 27 35
		f 4 -25 28 29 30
		mu 0 4 20 28 29 21
		f 4 -30 31 32 33
		mu 0 4 21 29 30 22
		f 4 -33 34 35 36
		mu 0 4 22 30 31 23
		f 4 -36 37 38 39
		mu 0 4 23 31 32 24
		f 4 -39 40 41 42
		mu 0 4 24 32 33 25
		f 4 -42 43 44 45
		mu 0 4 25 33 34 26
		f 4 -45 46 -27 47
		mu 0 4 26 34 35 27
		f 4 49 -29 48 -4
		mu 0 4 2 29 28 0
		f 4 50 -32 -50 -10
		mu 0 4 3 30 29 2
		f 4 51 -35 -51 -13
		mu 0 4 4 31 30 3
		f 4 52 -38 -52 -16
		mu 0 4 5 32 31 4
		f 4 53 -41 -53 -19
		mu 0 4 6 33 32 5
		f 4 54 -44 -54 -22
		mu 0 4 7 34 33 6
		f 4 55 -47 -55 -24
		mu 0 4 8 35 34 7
		f 4 -49 -28 -56 -5
		mu 0 4 9 18 35 8;
	setAttr ".cd" -type "dataPolyComponent" Index_Data Edge 0 ;
	setAttr ".cvd" -type "dataPolyComponent" Index_Data Vertex 0 ;
	setAttr ".pd[0]" -type "dataPolyComponent" Index_Data UV 0 ;
	setAttr ".hfd" -type "dataPolyComponent" Index_Data Face 0 ;
createNode transform -n "ring01";
	rename -uid "956892A2-45FB-D20F-45D8-B89EF0F2A264";
createNode mesh -n "ringShape1" -p "ring01";
	rename -uid "0345878B-4949-0B8B-FFB9-1380B9FE736B";
	setAttr -k off ".v";
	setAttr ".vir" yes;
	setAttr ".vif" yes;
	setAttr ".pv" -type "double2" 0.375 0.87501257658004761 ;
	setAttr ".uvst[0].uvsn" -type "string" "map1";
	setAttr -s 45 ".uvst[0].uvsp[0:44]" -type "float2" 0 0.62498748 1 0.37501252
		 0.125 0.62498748 0.25 0.62498748 0.375 0.62498748 0.5 0.62498748 0.625 0.62498748
		 0.75 0.62498748 0.875 0.62498748 1 0.62498748 0 0.37501252 0.125 0.37501252 0.25
		 0.37501252 0.375 0.37501252 0.5 0.37501252 0.625 0.37501246 0.75 0.37501255 0.875
		 0.37501249 1 0.87501252 1 1 0 1 0.125 1 0.25 1 0.375 1 0.5 1 0.625 1 0.75 1 0.875
		 1 2.4934157e-09 0.87501252 0.125 0.87501258 0.25 0.87501252 0.375 0.87501258 0.5
		 0.87501252 0.625 0.87501252 0.75 0.87501258 0.875 0.87501252 1.4269655e-09 0.12498747
		 0.125 0.12498744 0.25 0.12498747 0.375 0.12498746 0.5 0.12498748 0.625 0.1249875
		 0.75 0.12498754 0.875 0.12498754 1 0.12498748;
	setAttr ".cuvs" -type "string" "map1";
	setAttr ".dcc" -type "string" "Ambient+Diffuse";
	setAttr ".covm[0]"  0 1 1;
	setAttr ".cdvm[0]"  0 1 1;
	setAttr -s 32 ".pt[0:31]" -type "float3"  -0.84319615 -0.089477807 
		0.64398235 -0.84319615 0.089477837 0.64398235 -0.14086539 -0.089477807 1.0515938 
		-0.14086539 0.089477837 1.0515938 0.64398235 -0.089477807 0.84319615 0.64398235 0.089477837 
		0.84319615 1.0515939 -0.089477807 0.14086545 1.0515939 0.089477837 0.14086545 0.84319615 
		-0.089477807 -0.64398241 0.84319615 0.089477837 -0.64398241 0.14086545 -0.089477807 
		-1.051594 0.14086545 0.089477837 -1.0515939 -0.64398235 -0.089477807 -0.84319627 
		-0.64398235 0.089477837 -0.84319621 -1.051594 -0.089477807 -0.14086542 -1.0515939 
		0.089477837 -0.14086542 -0.72520339 -0.089477807 0.55386662 -0.72520339 0.089477777 
		0.55386662 -0.12115341 -0.089477807 0.90443933 -0.12115341 0.089477777 0.90443933 
		0.55386668 -0.089477807 0.72520351 0.55386662 0.089477777 0.72520345 0.90443945 -0.089477807 
		0.12115347 0.90443945 0.089477777 0.12115347 0.72520351 -0.089477807 -0.55386662 
		0.72520345 0.089477777 -0.55386662 0.12115347 -0.089477807 -0.90443951 0.12115347 
		0.089477777 -0.90443951 -0.55386668 -0.089477807 -0.72520345 -0.55386668 0.089477837 
		-0.72520345 -0.90443951 -0.089477807 -0.12115344 -0.90443951 0.089477777 -0.12115344;
	setAttr -s 32 ".vt[0:31]"  0.9840616 0.12165593 -0.9840616 0.9840616 -0.121656 -0.9840616
		 -8.4428935e-09 0.12165593 -1.39167309 -8.4428953e-09 -0.121656 -1.39167309 -0.9840616 0.12165594 -0.9840616
		 -0.9840616 -0.12165599 -0.9840616 -1.39167309 0.12165593 -4.2214467e-09 -1.39167309 -0.121656 -4.2214476e-09
		 -0.9840616 0.12165593 0.9840616 -0.9840616 -0.121656 0.9840616 -8.4428944e-09 0.12165594 1.39167321
		 -8.4428944e-09 -0.12165599 1.39167309 0.98406166 0.12165595 0.98406166 0.9840616 -0.12165598 0.9840616
		 1.39167333 0.12165594 -4.2214472e-09 1.39167321 -0.12165599 -4.2214472e-09 0.84635687 0.12165596 -0.84635693
		 0.84635687 -0.12165596 -0.84635687 -2.1715008e-08 0.12165596 -1.19692969 -2.1715008e-08 -0.12165596 -1.19692969
		 -0.84635693 0.12165595 -0.84635693 -0.84635687 -0.12165597 -0.84635687 -1.19692969 0.12165596 -1.6165723e-08
		 -1.19692969 -0.12165596 -1.6165723e-08 -0.84635693 0.12165596 0.84635687 -0.84635687 -0.12165596 0.84635687
		 -2.1715008e-08 0.12165595 1.19692981 -2.1715008e-08 -0.12165597 1.19692981 0.84635693 0.12165594 0.84635693
		 0.84635693 -0.12165598 0.84635693 1.19692993 0.12165595 -8.8273113e-09 1.19692993 -0.12165597 -8.8273113e-09;
	setAttr -s 64 ".ed[0:63]"  0 1 0 1 3 0 3 2 0 2 0 0 0 14 0 14 15 0 15 1 0
		 3 5 0 5 4 0 4 2 0 5 7 0 7 6 0 6 4 0 7 9 0 9 8 0 8 6 0 9 11 0 11 10 0 10 8 0 11 13 0
		 13 12 0 12 10 0 13 15 0 14 12 0 16 17 0 17 31 0 31 30 0 30 16 0 16 18 0 18 19 0 19 17 0
		 18 20 0 20 21 0 21 19 0 20 22 0 22 23 0 23 21 0 22 24 0 24 25 0 25 23 0 24 26 0 26 27 0
		 27 25 0 26 28 0 28 29 0 29 27 0 28 30 0 31 29 0 16 0 0 2 18 0 4 20 0 6 22 0 8 24 0
		 10 26 0 12 28 0 14 30 0 1 17 0 19 3 0 21 5 0 23 7 0 25 9 0 27 11 0 29 13 0 31 15 0;
	setAttr -s 32 -ch 128 ".fc[0:31]" -type "polyFaces" 
		f 4 0 1 2 3
		mu 0 4 0 10 11 2
		f 4 -1 4 5 6
		mu 0 4 1 9 8 17
		f 4 -3 7 8 9
		mu 0 4 2 11 12 3
		f 4 -9 10 11 12
		mu 0 4 3 12 13 4
		f 4 -12 13 14 15
		mu 0 4 4 13 14 5
		f 4 -15 16 17 18
		mu 0 4 5 14 15 6
		f 4 -18 19 20 21
		mu 0 4 6 15 16 7
		f 4 -21 22 -6 23
		mu 0 4 7 16 17 8
		f 4 -2 56 -31 57
		mu 0 4 11 10 36 37
		f 4 -8 -58 -34 58
		mu 0 4 12 11 37 38
		f 4 -11 -59 -37 59
		mu 0 4 13 12 38 39
		f 4 -14 -60 -40 60
		mu 0 4 14 13 39 40
		f 4 -17 -61 -43 61
		mu 0 4 15 14 40 41
		f 4 -20 -62 -46 62
		mu 0 4 16 15 41 42
		f 4 -23 -63 -48 63
		mu 0 4 17 16 42 43
		f 4 -7 -64 -26 -57
		mu 0 4 1 17 43 44
		f 4 24 25 26 27
		mu 0 4 18 19 27 35
		f 4 -25 28 29 30
		mu 0 4 20 28 29 21
		f 4 -30 31 32 33
		mu 0 4 21 29 30 22
		f 4 -33 34 35 36
		mu 0 4 22 30 31 23
		f 4 -36 37 38 39
		mu 0 4 23 31 32 24
		f 4 -39 40 41 42
		mu 0 4 24 32 33 25
		f 4 -42 43 44 45
		mu 0 4 25 33 34 26
		f 4 -45 46 -27 47
		mu 0 4 26 34 35 27
		f 4 49 -29 48 -4
		mu 0 4 2 29 28 0
		f 4 50 -32 -50 -10
		mu 0 4 3 30 29 2
		f 4 51 -35 -51 -13
		mu 0 4 4 31 30 3
		f 4 52 -38 -52 -16
		mu 0 4 5 32 31 4
		f 4 53 -41 -53 -19
		mu 0 4 6 33 32 5
		f 4 54 -44 -54 -22
		mu 0 4 7 34 33 6
		f 4 55 -47 -55 -24
		mu 0 4 8 35 34 7
		f 4 -49 -28 -56 -5
		mu 0 4 9 18 35 8;
	setAttr ".cd" -type "dataPolyComponent" Index_Data Edge 0 ;
	setAttr ".cvd" -type "dataPolyComponent" Index_Data Vertex 0 ;
	setAttr ".pd[0]" -type "dataPolyComponent" Index_Data UV 0 ;
	setAttr ".hfd" -type "dataPolyComponent" Index_Data Face 0 ;
createNode lightLinker -s -n "lightLinker1";
	rename -uid "AE4D1346-418C-C832-F6ED-9D8AC499760F";
	setAttr -s 2 ".lnk";
	setAttr -s 2 ".slnk";
createNode shapeEditorManager -n "shapeEditorManager";
	rename -uid "79977E6F-4E9D-3CE0-E058-7D8543BC71E0";
createNode poseInterpolatorManager -n "poseInterpolatorManager";
	rename -uid "1ED5EEE2-4DFB-29F5-43DF-668501D202DA";
createNode displayLayerManager -n "layerManager";
	rename -uid "6D5CB6D3-43EB-144D-D7A9-7698DF576596";
createNode displayLayer -n "defaultLayer";
	rename -uid "F03A1BDF-427F-C766-102B-2F89BA7311F8";
createNode renderLayerManager -n "renderLayerManager";
	rename -uid "C6F54C4A-4CBC-7CDA-F8B8-749FC8E50EDB";
createNode renderLayer -n "defaultRenderLayer";
	rename -uid "AEB083F0-4C94-86FE-3510-CC97EB868E99";
	setAttr ".g" yes;
createNode script -n "uiConfigurationScriptNode";
	rename -uid "C8709D7F-43DA-35FA-4225-81BE94BF9933";
	setAttr ".b" -type "string" (
		"// Maya Mel UI Configuration File.\n//\n//  This script is machine generated.  Edit at your own risk.\n//\n//\n\nglobal string $gMainPane;\nif (`paneLayout -exists $gMainPane`) {\n\n\tglobal int $gUseScenePanelConfig;\n\tint    $useSceneConfig = $gUseScenePanelConfig;\n\tint    $nodeEditorPanelVisible = stringArrayContains(\"nodeEditorPanel1\", `getPanel -vis`);\n\tint    $nodeEditorWorkspaceControlOpen = (`workspaceControl -exists nodeEditorPanel1Window` && `workspaceControl -q -visible nodeEditorPanel1Window`);\n\tint    $menusOkayInPanels = `optionVar -q allowMenusInPanels`;\n\tint    $nVisPanes = `paneLayout -q -nvp $gMainPane`;\n\tint    $nPanes = 0;\n\tstring $editorName;\n\tstring $panelName;\n\tstring $itemFilterName;\n\tstring $panelConfig;\n\n\t//\n\t//  get current state of the UI\n\t//\n\tsceneUIReplacement -update $gMainPane;\n\n\t$panelName = `sceneUIReplacement -getNextPanel \"modelPanel\" (localizedPanelLabel(\"Top View\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tmodelPanel -edit -l (localizedPanelLabel(\"Top View\")) -mbv $menusOkayInPanels  $panelName;\n"
		+ "\t\t$editorName = $panelName;\n        modelEditor -e \n            -camera \"top\" \n            -useInteractiveMode 0\n            -displayLights \"default\" \n            -displayAppearance \"smoothShaded\" \n            -activeOnly 0\n            -ignorePanZoom 0\n            -wireframeOnShaded 0\n            -headsUpDisplay 1\n            -holdOuts 1\n            -selectionHiliteDisplay 1\n            -useDefaultMaterial 0\n            -bufferMode \"double\" \n            -twoSidedLighting 0\n            -backfaceCulling 0\n            -xray 0\n            -jointXray 0\n            -activeComponentsXray 0\n            -displayTextures 0\n            -smoothWireframe 0\n            -lineWidth 1\n            -textureAnisotropic 0\n            -textureHilight 1\n            -textureSampling 2\n            -textureDisplay \"modulate\" \n            -textureMaxSize 32768\n            -fogging 0\n            -fogSource \"fragment\" \n            -fogMode \"linear\" \n            -fogStart 0\n            -fogEnd 100\n            -fogDensity 0.1\n            -fogColor 0.5 0.5 0.5 1 \n"
		+ "            -depthOfFieldPreview 1\n            -maxConstantTransparency 1\n            -rendererName \"vp2Renderer\" \n            -objectFilterShowInHUD 1\n            -isFiltered 0\n            -colorResolution 256 256 \n            -bumpResolution 512 512 \n            -textureCompression 0\n            -transparencyAlgorithm \"frontAndBackCull\" \n            -transpInShadows 0\n            -cullingOverride \"none\" \n            -lowQualityLighting 0\n            -maximumNumHardwareLights 1\n            -occlusionCulling 0\n            -shadingModel 0\n            -useBaseRenderer 0\n            -useReducedRenderer 0\n            -smallObjectCulling 0\n            -smallObjectThreshold -1 \n            -interactiveDisableShadows 0\n            -interactiveBackFaceCull 0\n            -sortTransparent 1\n            -controllers 1\n            -nurbsCurves 1\n            -nurbsSurfaces 1\n            -polymeshes 1\n            -subdivSurfaces 1\n            -planes 1\n            -lights 1\n            -cameras 1\n            -controlVertices 1\n"
		+ "            -hulls 1\n            -grid 1\n            -imagePlane 1\n            -joints 1\n            -ikHandles 1\n            -deformers 1\n            -dynamics 1\n            -particleInstancers 1\n            -fluids 1\n            -hairSystems 1\n            -follicles 1\n            -nCloths 1\n            -nParticles 1\n            -nRigids 1\n            -dynamicConstraints 1\n            -locators 1\n            -manipulators 1\n            -pluginShapes 1\n            -dimensions 1\n            -handles 1\n            -pivots 1\n            -textures 1\n            -strokes 1\n            -motionTrails 1\n            -clipGhosts 1\n            -greasePencils 1\n            -shadows 0\n            -captureSequenceNumber -1\n            -width 555\n            -height 335\n            -sceneRenderFilter 0\n            $editorName;\n        modelEditor -e -viewSelected 0 $editorName;\n        modelEditor -e \n            -pluginObjects \"gpuCacheDisplayFilter\" 1 \n            $editorName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n"
		+ "\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextPanel \"modelPanel\" (localizedPanelLabel(\"Side View\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tmodelPanel -edit -l (localizedPanelLabel(\"Side View\")) -mbv $menusOkayInPanels  $panelName;\n\t\t$editorName = $panelName;\n        modelEditor -e \n            -camera \"side\" \n            -useInteractiveMode 0\n            -displayLights \"default\" \n            -displayAppearance \"smoothShaded\" \n            -activeOnly 0\n            -ignorePanZoom 0\n            -wireframeOnShaded 0\n            -headsUpDisplay 1\n            -holdOuts 1\n            -selectionHiliteDisplay 1\n            -useDefaultMaterial 0\n            -bufferMode \"double\" \n            -twoSidedLighting 0\n            -backfaceCulling 0\n            -xray 0\n            -jointXray 0\n            -activeComponentsXray 0\n            -displayTextures 0\n            -smoothWireframe 0\n            -lineWidth 1\n            -textureAnisotropic 0\n            -textureHilight 1\n            -textureSampling 2\n"
		+ "            -textureDisplay \"modulate\" \n            -textureMaxSize 32768\n            -fogging 0\n            -fogSource \"fragment\" \n            -fogMode \"linear\" \n            -fogStart 0\n            -fogEnd 100\n            -fogDensity 0.1\n            -fogColor 0.5 0.5 0.5 1 \n            -depthOfFieldPreview 1\n            -maxConstantTransparency 1\n            -rendererName \"vp2Renderer\" \n            -objectFilterShowInHUD 1\n            -isFiltered 0\n            -colorResolution 256 256 \n            -bumpResolution 512 512 \n            -textureCompression 0\n            -transparencyAlgorithm \"frontAndBackCull\" \n            -transpInShadows 0\n            -cullingOverride \"none\" \n            -lowQualityLighting 0\n            -maximumNumHardwareLights 1\n            -occlusionCulling 0\n            -shadingModel 0\n            -useBaseRenderer 0\n            -useReducedRenderer 0\n            -smallObjectCulling 0\n            -smallObjectThreshold -1 \n            -interactiveDisableShadows 0\n            -interactiveBackFaceCull 0\n"
		+ "            -sortTransparent 1\n            -controllers 1\n            -nurbsCurves 1\n            -nurbsSurfaces 1\n            -polymeshes 1\n            -subdivSurfaces 1\n            -planes 1\n            -lights 1\n            -cameras 1\n            -controlVertices 1\n            -hulls 1\n            -grid 1\n            -imagePlane 1\n            -joints 1\n            -ikHandles 1\n            -deformers 1\n            -dynamics 1\n            -particleInstancers 1\n            -fluids 1\n            -hairSystems 1\n            -follicles 1\n            -nCloths 1\n            -nParticles 1\n            -nRigids 1\n            -dynamicConstraints 1\n            -locators 1\n            -manipulators 1\n            -pluginShapes 1\n            -dimensions 1\n            -handles 1\n            -pivots 1\n            -textures 1\n            -strokes 1\n            -motionTrails 1\n            -clipGhosts 1\n            -greasePencils 1\n            -shadows 0\n            -captureSequenceNumber -1\n            -width 555\n            -height 335\n"
		+ "            -sceneRenderFilter 0\n            $editorName;\n        modelEditor -e -viewSelected 0 $editorName;\n        modelEditor -e \n            -pluginObjects \"gpuCacheDisplayFilter\" 1 \n            $editorName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextPanel \"modelPanel\" (localizedPanelLabel(\"Front View\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tmodelPanel -edit -l (localizedPanelLabel(\"Front View\")) -mbv $menusOkayInPanels  $panelName;\n\t\t$editorName = $panelName;\n        modelEditor -e \n            -camera \"front\" \n            -useInteractiveMode 0\n            -displayLights \"default\" \n            -displayAppearance \"smoothShaded\" \n            -activeOnly 0\n            -ignorePanZoom 0\n            -wireframeOnShaded 0\n            -headsUpDisplay 1\n            -holdOuts 1\n            -selectionHiliteDisplay 1\n            -useDefaultMaterial 0\n            -bufferMode \"double\" \n            -twoSidedLighting 0\n            -backfaceCulling 0\n"
		+ "            -xray 0\n            -jointXray 0\n            -activeComponentsXray 0\n            -displayTextures 0\n            -smoothWireframe 0\n            -lineWidth 1\n            -textureAnisotropic 0\n            -textureHilight 1\n            -textureSampling 2\n            -textureDisplay \"modulate\" \n            -textureMaxSize 32768\n            -fogging 0\n            -fogSource \"fragment\" \n            -fogMode \"linear\" \n            -fogStart 0\n            -fogEnd 100\n            -fogDensity 0.1\n            -fogColor 0.5 0.5 0.5 1 \n            -depthOfFieldPreview 1\n            -maxConstantTransparency 1\n            -rendererName \"vp2Renderer\" \n            -objectFilterShowInHUD 1\n            -isFiltered 0\n            -colorResolution 256 256 \n            -bumpResolution 512 512 \n            -textureCompression 0\n            -transparencyAlgorithm \"frontAndBackCull\" \n            -transpInShadows 0\n            -cullingOverride \"none\" \n            -lowQualityLighting 0\n            -maximumNumHardwareLights 1\n            -occlusionCulling 0\n"
		+ "            -shadingModel 0\n            -useBaseRenderer 0\n            -useReducedRenderer 0\n            -smallObjectCulling 0\n            -smallObjectThreshold -1 \n            -interactiveDisableShadows 0\n            -interactiveBackFaceCull 0\n            -sortTransparent 1\n            -controllers 1\n            -nurbsCurves 1\n            -nurbsSurfaces 1\n            -polymeshes 1\n            -subdivSurfaces 1\n            -planes 1\n            -lights 1\n            -cameras 1\n            -controlVertices 1\n            -hulls 1\n            -grid 1\n            -imagePlane 1\n            -joints 1\n            -ikHandles 1\n            -deformers 1\n            -dynamics 1\n            -particleInstancers 1\n            -fluids 1\n            -hairSystems 1\n            -follicles 1\n            -nCloths 1\n            -nParticles 1\n            -nRigids 1\n            -dynamicConstraints 1\n            -locators 1\n            -manipulators 1\n            -pluginShapes 1\n            -dimensions 1\n            -handles 1\n            -pivots 1\n"
		+ "            -textures 1\n            -strokes 1\n            -motionTrails 1\n            -clipGhosts 1\n            -greasePencils 1\n            -shadows 0\n            -captureSequenceNumber -1\n            -width 555\n            -height 335\n            -sceneRenderFilter 0\n            $editorName;\n        modelEditor -e -viewSelected 0 $editorName;\n        modelEditor -e \n            -pluginObjects \"gpuCacheDisplayFilter\" 1 \n            $editorName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextPanel \"modelPanel\" (localizedPanelLabel(\"Persp View\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tmodelPanel -edit -l (localizedPanelLabel(\"Persp View\")) -mbv $menusOkayInPanels  $panelName;\n\t\t$editorName = $panelName;\n        modelEditor -e \n            -camera \"persp\" \n            -useInteractiveMode 0\n            -displayLights \"default\" \n            -displayAppearance \"smoothShaded\" \n            -activeOnly 0\n            -ignorePanZoom 0\n"
		+ "            -wireframeOnShaded 0\n            -headsUpDisplay 1\n            -holdOuts 1\n            -selectionHiliteDisplay 1\n            -useDefaultMaterial 0\n            -bufferMode \"double\" \n            -twoSidedLighting 0\n            -backfaceCulling 0\n            -xray 0\n            -jointXray 0\n            -activeComponentsXray 0\n            -displayTextures 0\n            -smoothWireframe 0\n            -lineWidth 1\n            -textureAnisotropic 0\n            -textureHilight 1\n            -textureSampling 2\n            -textureDisplay \"modulate\" \n            -textureMaxSize 32768\n            -fogging 0\n            -fogSource \"fragment\" \n            -fogMode \"linear\" \n            -fogStart 0\n            -fogEnd 100\n            -fogDensity 0.1\n            -fogColor 0.5 0.5 0.5 1 \n            -depthOfFieldPreview 1\n            -maxConstantTransparency 1\n            -rendererName \"vp2Renderer\" \n            -objectFilterShowInHUD 1\n            -isFiltered 0\n            -colorResolution 256 256 \n            -bumpResolution 512 512 \n"
		+ "            -textureCompression 0\n            -transparencyAlgorithm \"frontAndBackCull\" \n            -transpInShadows 0\n            -cullingOverride \"none\" \n            -lowQualityLighting 0\n            -maximumNumHardwareLights 1\n            -occlusionCulling 0\n            -shadingModel 0\n            -useBaseRenderer 0\n            -useReducedRenderer 0\n            -smallObjectCulling 0\n            -smallObjectThreshold -1 \n            -interactiveDisableShadows 0\n            -interactiveBackFaceCull 0\n            -sortTransparent 1\n            -controllers 1\n            -nurbsCurves 1\n            -nurbsSurfaces 1\n            -polymeshes 1\n            -subdivSurfaces 1\n            -planes 1\n            -lights 1\n            -cameras 1\n            -controlVertices 1\n            -hulls 1\n            -grid 1\n            -imagePlane 1\n            -joints 1\n            -ikHandles 1\n            -deformers 1\n            -dynamics 1\n            -particleInstancers 1\n            -fluids 1\n            -hairSystems 1\n            -follicles 1\n"
		+ "            -nCloths 1\n            -nParticles 1\n            -nRigids 1\n            -dynamicConstraints 1\n            -locators 1\n            -manipulators 1\n            -pluginShapes 1\n            -dimensions 1\n            -handles 1\n            -pivots 1\n            -textures 1\n            -strokes 1\n            -motionTrails 1\n            -clipGhosts 1\n            -greasePencils 1\n            -shadows 0\n            -captureSequenceNumber -1\n            -width 1600\n            -height 811\n            -sceneRenderFilter 0\n            $editorName;\n        modelEditor -e -viewSelected 0 $editorName;\n        modelEditor -e \n            -pluginObjects \"gpuCacheDisplayFilter\" 1 \n            $editorName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextPanel \"outlinerPanel\" (localizedPanelLabel(\"ToggledOutliner\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\toutlinerPanel -edit -l (localizedPanelLabel(\"ToggledOutliner\")) -mbv $menusOkayInPanels  $panelName;\n"
		+ "\t\t$editorName = $panelName;\n        outlinerEditor -e \n            -showShapes 0\n            -showAssignedMaterials 0\n            -showTimeEditor 1\n            -showReferenceNodes 1\n            -showReferenceMembers 1\n            -showAttributes 0\n            -showConnected 0\n            -showAnimCurvesOnly 0\n            -showMuteInfo 0\n            -organizeByLayer 1\n            -organizeByClip 1\n            -showAnimLayerWeight 1\n            -autoExpandLayers 1\n            -autoExpand 0\n            -showDagOnly 1\n            -showAssets 1\n            -showContainedOnly 1\n            -showPublishedAsConnected 0\n            -showParentContainers 0\n            -showContainerContents 1\n            -ignoreDagHierarchy 0\n            -expandConnections 0\n            -showUpstreamCurves 1\n            -showUnitlessCurves 1\n            -showCompounds 1\n            -showLeafs 1\n            -showNumericAttrsOnly 0\n            -highlightActive 1\n            -autoSelectNewObjects 0\n            -doNotSelectNewObjects 0\n            -dropIsParent 1\n"
		+ "            -transmitFilters 0\n            -setFilter \"defaultSetFilter\" \n            -showSetMembers 1\n            -allowMultiSelection 1\n            -alwaysToggleSelect 0\n            -directSelect 0\n            -isSet 0\n            -isSetMember 0\n            -displayMode \"DAG\" \n            -expandObjects 0\n            -setsIgnoreFilters 1\n            -containersIgnoreFilters 0\n            -editAttrName 0\n            -showAttrValues 0\n            -highlightSecondary 0\n            -showUVAttrsOnly 0\n            -showTextureNodesOnly 0\n            -attrAlphaOrder \"default\" \n            -animLayerFilterOptions \"allAffecting\" \n            -sortOrder \"none\" \n            -longNames 0\n            -niceNames 1\n            -showNamespace 1\n            -showPinIcons 0\n            -mapMotionTrails 0\n            -ignoreHiddenAttribute 0\n            -ignoreOutlinerColor 0\n            -renderFilterVisible 0\n            -renderFilterIndex 0\n            -selectionOrder \"chronological\" \n            -expandAttribute 0\n            $editorName;\n"
		+ "\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextPanel \"outlinerPanel\" (localizedPanelLabel(\"Outliner\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\toutlinerPanel -edit -l (localizedPanelLabel(\"Outliner\")) -mbv $menusOkayInPanels  $panelName;\n\t\t$editorName = $panelName;\n        outlinerEditor -e \n            -showShapes 0\n            -showAssignedMaterials 0\n            -showTimeEditor 1\n            -showReferenceNodes 0\n            -showReferenceMembers 0\n            -showAttributes 0\n            -showConnected 0\n            -showAnimCurvesOnly 0\n            -showMuteInfo 0\n            -organizeByLayer 1\n            -organizeByClip 1\n            -showAnimLayerWeight 1\n            -autoExpandLayers 1\n            -autoExpand 0\n            -showDagOnly 1\n            -showAssets 1\n            -showContainedOnly 1\n            -showPublishedAsConnected 0\n            -showParentContainers 0\n            -showContainerContents 1\n            -ignoreDagHierarchy 0\n"
		+ "            -expandConnections 0\n            -showUpstreamCurves 1\n            -showUnitlessCurves 1\n            -showCompounds 1\n            -showLeafs 1\n            -showNumericAttrsOnly 0\n            -highlightActive 1\n            -autoSelectNewObjects 0\n            -doNotSelectNewObjects 0\n            -dropIsParent 1\n            -transmitFilters 0\n            -setFilter \"defaultSetFilter\" \n            -showSetMembers 1\n            -allowMultiSelection 1\n            -alwaysToggleSelect 0\n            -directSelect 0\n            -displayMode \"DAG\" \n            -expandObjects 0\n            -setsIgnoreFilters 1\n            -containersIgnoreFilters 0\n            -editAttrName 0\n            -showAttrValues 0\n            -highlightSecondary 0\n            -showUVAttrsOnly 0\n            -showTextureNodesOnly 0\n            -attrAlphaOrder \"default\" \n            -animLayerFilterOptions \"allAffecting\" \n            -sortOrder \"none\" \n            -longNames 0\n            -niceNames 1\n            -showNamespace 1\n            -showPinIcons 0\n"
		+ "            -mapMotionTrails 0\n            -ignoreHiddenAttribute 0\n            -ignoreOutlinerColor 0\n            -renderFilterVisible 0\n            $editorName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextScriptedPanel \"graphEditor\" (localizedPanelLabel(\"Graph Editor\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tscriptedPanel -edit -l (localizedPanelLabel(\"Graph Editor\")) -mbv $menusOkayInPanels  $panelName;\n\n\t\t\t$editorName = ($panelName+\"OutlineEd\");\n            outlinerEditor -e \n                -showShapes 1\n                -showAssignedMaterials 0\n                -showTimeEditor 1\n                -showReferenceNodes 0\n                -showReferenceMembers 0\n                -showAttributes 1\n                -showConnected 1\n                -showAnimCurvesOnly 1\n                -showMuteInfo 0\n                -organizeByLayer 1\n                -organizeByClip 1\n                -showAnimLayerWeight 1\n                -autoExpandLayers 1\n"
		+ "                -autoExpand 1\n                -showDagOnly 0\n                -showAssets 1\n                -showContainedOnly 0\n                -showPublishedAsConnected 0\n                -showParentContainers 0\n                -showContainerContents 0\n                -ignoreDagHierarchy 0\n                -expandConnections 1\n                -showUpstreamCurves 1\n                -showUnitlessCurves 1\n                -showCompounds 0\n                -showLeafs 1\n                -showNumericAttrsOnly 1\n                -highlightActive 0\n                -autoSelectNewObjects 1\n                -doNotSelectNewObjects 0\n                -dropIsParent 1\n                -transmitFilters 1\n                -setFilter \"0\" \n                -showSetMembers 0\n                -allowMultiSelection 1\n                -alwaysToggleSelect 0\n                -directSelect 0\n                -displayMode \"DAG\" \n                -expandObjects 0\n                -setsIgnoreFilters 1\n                -containersIgnoreFilters 0\n                -editAttrName 0\n"
		+ "                -showAttrValues 0\n                -highlightSecondary 0\n                -showUVAttrsOnly 0\n                -showTextureNodesOnly 0\n                -attrAlphaOrder \"default\" \n                -animLayerFilterOptions \"allAffecting\" \n                -sortOrder \"none\" \n                -longNames 0\n                -niceNames 1\n                -showNamespace 1\n                -showPinIcons 1\n                -mapMotionTrails 1\n                -ignoreHiddenAttribute 0\n                -ignoreOutlinerColor 0\n                -renderFilterVisible 0\n                $editorName;\n\n\t\t\t$editorName = ($panelName+\"GraphEd\");\n            animCurveEditor -e \n                -displayValues 0\n                -snapTime \"integer\" \n                -snapValue \"none\" \n                -showPlayRangeShades \"on\" \n                -lockPlayRangeShades \"off\" \n                -smoothness \"fine\" \n                -resultSamples 1\n                -resultScreenSamples 0\n                -resultUpdate \"delayed\" \n                -showUpstreamCurves 1\n"
		+ "                -stackedCurvesMin -1\n                -stackedCurvesMax 1\n                -stackedCurvesSpace 0.2\n                -preSelectionHighlight 0\n                -constrainDrag 0\n                -valueLinesToggle 0\n                -highlightAffectedCurves 0\n                $editorName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextScriptedPanel \"dopeSheetPanel\" (localizedPanelLabel(\"Dope Sheet\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tscriptedPanel -edit -l (localizedPanelLabel(\"Dope Sheet\")) -mbv $menusOkayInPanels  $panelName;\n\n\t\t\t$editorName = ($panelName+\"OutlineEd\");\n            outlinerEditor -e \n                -showShapes 1\n                -showAssignedMaterials 0\n                -showTimeEditor 1\n                -showReferenceNodes 0\n                -showReferenceMembers 0\n                -showAttributes 1\n                -showConnected 1\n                -showAnimCurvesOnly 1\n                -showMuteInfo 0\n"
		+ "                -organizeByLayer 1\n                -organizeByClip 1\n                -showAnimLayerWeight 1\n                -autoExpandLayers 1\n                -autoExpand 0\n                -showDagOnly 0\n                -showAssets 1\n                -showContainedOnly 0\n                -showPublishedAsConnected 0\n                -showParentContainers 0\n                -showContainerContents 0\n                -ignoreDagHierarchy 0\n                -expandConnections 1\n                -showUpstreamCurves 1\n                -showUnitlessCurves 0\n                -showCompounds 1\n                -showLeafs 1\n                -showNumericAttrsOnly 1\n                -highlightActive 0\n                -autoSelectNewObjects 0\n                -doNotSelectNewObjects 1\n                -dropIsParent 1\n                -transmitFilters 0\n                -setFilter \"0\" \n                -showSetMembers 0\n                -allowMultiSelection 1\n                -alwaysToggleSelect 0\n                -directSelect 0\n                -displayMode \"DAG\" \n"
		+ "                -expandObjects 0\n                -setsIgnoreFilters 1\n                -containersIgnoreFilters 0\n                -editAttrName 0\n                -showAttrValues 0\n                -highlightSecondary 0\n                -showUVAttrsOnly 0\n                -showTextureNodesOnly 0\n                -attrAlphaOrder \"default\" \n                -animLayerFilterOptions \"allAffecting\" \n                -sortOrder \"none\" \n                -longNames 0\n                -niceNames 1\n                -showNamespace 1\n                -showPinIcons 0\n                -mapMotionTrails 1\n                -ignoreHiddenAttribute 0\n                -ignoreOutlinerColor 0\n                -renderFilterVisible 0\n                $editorName;\n\n\t\t\t$editorName = ($panelName+\"DopeSheetEd\");\n            dopeSheetEditor -e \n                -displayValues 0\n                -snapTime \"integer\" \n                -snapValue \"none\" \n                -outliner \"dopeSheetPanel1OutlineEd\" \n                -showSummary 1\n                -showScene 0\n"
		+ "                -hierarchyBelow 0\n                -showTicks 1\n                -selectionWindow 0 0 0 0 \n                $editorName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextScriptedPanel \"timeEditorPanel\" (localizedPanelLabel(\"Time Editor\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tscriptedPanel -edit -l (localizedPanelLabel(\"Time Editor\")) -mbv $menusOkayInPanels  $panelName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextScriptedPanel \"clipEditorPanel\" (localizedPanelLabel(\"Trax Editor\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tscriptedPanel -edit -l (localizedPanelLabel(\"Trax Editor\")) -mbv $menusOkayInPanels  $panelName;\n\n\t\t\t$editorName = clipEditorNameFromPanel($panelName);\n            clipEditor -e \n                -displayValues 0\n                -snapTime \"none\" \n                -snapValue \"none\" \n                -initialized 0\n"
		+ "                -manageSequencer 0 \n                $editorName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextScriptedPanel \"sequenceEditorPanel\" (localizedPanelLabel(\"Camera Sequencer\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tscriptedPanel -edit -l (localizedPanelLabel(\"Camera Sequencer\")) -mbv $menusOkayInPanels  $panelName;\n\n\t\t\t$editorName = sequenceEditorNameFromPanel($panelName);\n            clipEditor -e \n                -displayValues 0\n                -snapTime \"none\" \n                -snapValue \"none\" \n                -initialized 0\n                -manageSequencer 1 \n                $editorName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextScriptedPanel \"hyperGraphPanel\" (localizedPanelLabel(\"Hypergraph Hierarchy\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tscriptedPanel -edit -l (localizedPanelLabel(\"Hypergraph Hierarchy\")) -mbv $menusOkayInPanels  $panelName;\n"
		+ "\n\t\t\t$editorName = ($panelName+\"HyperGraphEd\");\n            hyperGraph -e \n                -graphLayoutStyle \"hierarchicalLayout\" \n                -orientation \"horiz\" \n                -mergeConnections 0\n                -zoom 1\n                -animateTransition 0\n                -showRelationships 1\n                -showShapes 0\n                -showDeformers 0\n                -showExpressions 0\n                -showConstraints 0\n                -showConnectionFromSelected 0\n                -showConnectionToSelected 0\n                -showConstraintLabels 0\n                -showUnderworld 0\n                -showInvisible 0\n                -transitionFrames 1\n                -opaqueContainers 0\n                -freeform 0\n                -imagePosition 0 0 \n                -imageScale 1\n                -imageEnabled 0\n                -graphType \"DAG\" \n                -heatMapDisplay 0\n                -updateSelection 1\n                -updateNodeAdded 1\n                -useDrawOverrideColor 0\n                -limitGraphTraversal -1\n"
		+ "                -range 0 0 \n                -iconSize \"smallIcons\" \n                -showCachedConnections 0\n                $editorName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextScriptedPanel \"hyperShadePanel\" (localizedPanelLabel(\"Hypershade\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tscriptedPanel -edit -l (localizedPanelLabel(\"Hypershade\")) -mbv $menusOkayInPanels  $panelName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextScriptedPanel \"visorPanel\" (localizedPanelLabel(\"Visor\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tscriptedPanel -edit -l (localizedPanelLabel(\"Visor\")) -mbv $menusOkayInPanels  $panelName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextScriptedPanel \"nodeEditorPanel\" (localizedPanelLabel(\"Node Editor\")) `;\n\tif ($nodeEditorPanelVisible || $nodeEditorWorkspaceControlOpen) {\n"
		+ "\t\tif (\"\" == $panelName) {\n\t\t\tif ($useSceneConfig) {\n\t\t\t\t$panelName = `scriptedPanel -unParent  -type \"nodeEditorPanel\" -l (localizedPanelLabel(\"Node Editor\")) -mbv $menusOkayInPanels `;\n\n\t\t\t$editorName = ($panelName+\"NodeEditorEd\");\n            nodeEditor -e \n                -allAttributes 0\n                -allNodes 0\n                -autoSizeNodes 1\n                -consistentNameSize 1\n                -createNodeCommand \"nodeEdCreateNodeCommand\" \n                -connectNodeOnCreation 0\n                -connectOnDrop 0\n                -copyConnectionsOnPaste 0\n                -connectionStyle \"bezier\" \n                -defaultPinnedState 0\n                -additiveGraphingMode 0\n                -settingsChangedCallback \"nodeEdSyncControls\" \n                -traversalDepthLimit -1\n                -keyPressCommand \"nodeEdKeyPressCommand\" \n                -nodeTitleMode \"name\" \n                -gridSnap 0\n                -gridVisibility 1\n                -crosshairOnEdgeDragging 0\n                -popupMenuScript \"nodeEdBuildPanelMenus\" \n"
		+ "                -showNamespace 1\n                -showShapes 1\n                -showSGShapes 0\n                -showTransforms 1\n                -useAssets 1\n                -syncedSelection 1\n                -extendToShapes 1\n                -editorMode \"default\" \n                -hasWatchpoint 0\n                $editorName;\n\t\t\t}\n\t\t} else {\n\t\t\t$label = `panel -q -label $panelName`;\n\t\t\tscriptedPanel -edit -l (localizedPanelLabel(\"Node Editor\")) -mbv $menusOkayInPanels  $panelName;\n\n\t\t\t$editorName = ($panelName+\"NodeEditorEd\");\n            nodeEditor -e \n                -allAttributes 0\n                -allNodes 0\n                -autoSizeNodes 1\n                -consistentNameSize 1\n                -createNodeCommand \"nodeEdCreateNodeCommand\" \n                -connectNodeOnCreation 0\n                -connectOnDrop 0\n                -copyConnectionsOnPaste 0\n                -connectionStyle \"bezier\" \n                -defaultPinnedState 0\n                -additiveGraphingMode 0\n                -settingsChangedCallback \"nodeEdSyncControls\" \n"
		+ "                -traversalDepthLimit -1\n                -keyPressCommand \"nodeEdKeyPressCommand\" \n                -nodeTitleMode \"name\" \n                -gridSnap 0\n                -gridVisibility 1\n                -crosshairOnEdgeDragging 0\n                -popupMenuScript \"nodeEdBuildPanelMenus\" \n                -showNamespace 1\n                -showShapes 1\n                -showSGShapes 0\n                -showTransforms 1\n                -useAssets 1\n                -syncedSelection 1\n                -extendToShapes 1\n                -editorMode \"default\" \n                -hasWatchpoint 0\n                $editorName;\n\t\t\tif (!$useSceneConfig) {\n\t\t\t\tpanel -e -l $label $panelName;\n\t\t\t}\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextScriptedPanel \"createNodePanel\" (localizedPanelLabel(\"Create Node\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tscriptedPanel -edit -l (localizedPanelLabel(\"Create Node\")) -mbv $menusOkayInPanels  $panelName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n"
		+ "\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextScriptedPanel \"polyTexturePlacementPanel\" (localizedPanelLabel(\"UV Editor\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tscriptedPanel -edit -l (localizedPanelLabel(\"UV Editor\")) -mbv $menusOkayInPanels  $panelName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextScriptedPanel \"renderWindowPanel\" (localizedPanelLabel(\"Render View\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tscriptedPanel -edit -l (localizedPanelLabel(\"Render View\")) -mbv $menusOkayInPanels  $panelName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextPanel \"shapePanel\" (localizedPanelLabel(\"Shape Editor\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tshapePanel -edit -l (localizedPanelLabel(\"Shape Editor\")) -mbv $menusOkayInPanels  $panelName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n"
		+ "\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextPanel \"posePanel\" (localizedPanelLabel(\"Pose Editor\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tposePanel -edit -l (localizedPanelLabel(\"Pose Editor\")) -mbv $menusOkayInPanels  $panelName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextScriptedPanel \"dynRelEdPanel\" (localizedPanelLabel(\"Dynamic Relationships\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tscriptedPanel -edit -l (localizedPanelLabel(\"Dynamic Relationships\")) -mbv $menusOkayInPanels  $panelName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextScriptedPanel \"relationshipPanel\" (localizedPanelLabel(\"Relationship Editor\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tscriptedPanel -edit -l (localizedPanelLabel(\"Relationship Editor\")) -mbv $menusOkayInPanels  $panelName;\n\t\tif (!$useSceneConfig) {\n"
		+ "\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextScriptedPanel \"referenceEditorPanel\" (localizedPanelLabel(\"Reference Editor\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tscriptedPanel -edit -l (localizedPanelLabel(\"Reference Editor\")) -mbv $menusOkayInPanels  $panelName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextScriptedPanel \"componentEditorPanel\" (localizedPanelLabel(\"Component Editor\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tscriptedPanel -edit -l (localizedPanelLabel(\"Component Editor\")) -mbv $menusOkayInPanels  $panelName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextScriptedPanel \"dynPaintScriptedPanelType\" (localizedPanelLabel(\"Paint Effects\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tscriptedPanel -edit -l (localizedPanelLabel(\"Paint Effects\")) -mbv $menusOkayInPanels  $panelName;\n"
		+ "\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextScriptedPanel \"scriptEditorPanel\" (localizedPanelLabel(\"Script Editor\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tscriptedPanel -edit -l (localizedPanelLabel(\"Script Editor\")) -mbv $menusOkayInPanels  $panelName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextScriptedPanel \"profilerPanel\" (localizedPanelLabel(\"Profiler Tool\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tscriptedPanel -edit -l (localizedPanelLabel(\"Profiler Tool\")) -mbv $menusOkayInPanels  $panelName;\n\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\t$panelName = `sceneUIReplacement -getNextScriptedPanel \"contentBrowserPanel\" (localizedPanelLabel(\"Content Browser\")) `;\n\tif (\"\" != $panelName) {\n\t\t$label = `panel -q -label $panelName`;\n\t\tscriptedPanel -edit -l (localizedPanelLabel(\"Content Browser\")) -mbv $menusOkayInPanels  $panelName;\n"
		+ "\t\tif (!$useSceneConfig) {\n\t\t\tpanel -e -l $label $panelName;\n\t\t}\n\t}\n\n\n\tif ($useSceneConfig) {\n        string $configName = `getPanel -cwl (localizedPanelLabel(\"Current Layout\"))`;\n        if (\"\" != $configName) {\n\t\t\tpanelConfiguration -edit -label (localizedPanelLabel(\"Current Layout\")) \n\t\t\t\t-userCreated false\n\t\t\t\t-defaultImage \"\"\n\t\t\t\t-image \"\"\n\t\t\t\t-sc false\n\t\t\t\t-configString \"global string $gMainPane; paneLayout -e -cn \\\"single\\\" -ps 1 100 100 $gMainPane;\"\n\t\t\t\t-removeAllPanels\n\t\t\t\t-ap false\n\t\t\t\t\t(localizedPanelLabel(\"Persp View\")) \n\t\t\t\t\t\"modelPanel\"\n"
		+ "\t\t\t\t\t\"$panelName = `modelPanel -unParent -l (localizedPanelLabel(\\\"Persp View\\\")) -mbv $menusOkayInPanels `;\\n$editorName = $panelName;\\nmodelEditor -e \\n    -cam `findStartUpCamera persp` \\n    -useInteractiveMode 0\\n    -displayLights \\\"default\\\" \\n    -displayAppearance \\\"smoothShaded\\\" \\n    -activeOnly 0\\n    -ignorePanZoom 0\\n    -wireframeOnShaded 0\\n    -headsUpDisplay 1\\n    -holdOuts 1\\n    -selectionHiliteDisplay 1\\n    -useDefaultMaterial 0\\n    -bufferMode \\\"double\\\" \\n    -twoSidedLighting 0\\n    -backfaceCulling 0\\n    -xray 0\\n    -jointXray 0\\n    -activeComponentsXray 0\\n    -displayTextures 0\\n    -smoothWireframe 0\\n    -lineWidth 1\\n    -textureAnisotropic 0\\n    -textureHilight 1\\n    -textureSampling 2\\n    -textureDisplay \\\"modulate\\\" \\n    -textureMaxSize 32768\\n    -fogging 0\\n    -fogSource \\\"fragment\\\" \\n    -fogMode \\\"linear\\\" \\n    -fogStart 0\\n    -fogEnd 100\\n    -fogDensity 0.1\\n    -fogColor 0.5 0.5 0.5 1 \\n    -depthOfFieldPreview 1\\n    -maxConstantTransparency 1\\n    -rendererName \\\"vp2Renderer\\\" \\n    -objectFilterShowInHUD 1\\n    -isFiltered 0\\n    -colorResolution 256 256 \\n    -bumpResolution 512 512 \\n    -textureCompression 0\\n    -transparencyAlgorithm \\\"frontAndBackCull\\\" \\n    -transpInShadows 0\\n    -cullingOverride \\\"none\\\" \\n    -lowQualityLighting 0\\n    -maximumNumHardwareLights 1\\n    -occlusionCulling 0\\n    -shadingModel 0\\n    -useBaseRenderer 0\\n    -useReducedRenderer 0\\n    -smallObjectCulling 0\\n    -smallObjectThreshold -1 \\n    -interactiveDisableShadows 0\\n    -interactiveBackFaceCull 0\\n    -sortTransparent 1\\n    -controllers 1\\n    -nurbsCurves 1\\n    -nurbsSurfaces 1\\n    -polymeshes 1\\n    -subdivSurfaces 1\\n    -planes 1\\n    -lights 1\\n    -cameras 1\\n    -controlVertices 1\\n    -hulls 1\\n    -grid 1\\n    -imagePlane 1\\n    -joints 1\\n    -ikHandles 1\\n    -deformers 1\\n    -dynamics 1\\n    -particleInstancers 1\\n    -fluids 1\\n    -hairSystems 1\\n    -follicles 1\\n    -nCloths 1\\n    -nParticles 1\\n    -nRigids 1\\n    -dynamicConstraints 1\\n    -locators 1\\n    -manipulators 1\\n    -pluginShapes 1\\n    -dimensions 1\\n    -handles 1\\n    -pivots 1\\n    -textures 1\\n    -strokes 1\\n    -motionTrails 1\\n    -clipGhosts 1\\n    -greasePencils 1\\n    -shadows 0\\n    -captureSequenceNumber -1\\n    -width 1600\\n    -height 811\\n    -sceneRenderFilter 0\\n    $editorName;\\nmodelEditor -e -viewSelected 0 $editorName;\\nmodelEditor -e \\n    -pluginObjects \\\"gpuCacheDisplayFilter\\\" 1 \\n    $editorName\"\n"
		+ "\t\t\t\t\t\"modelPanel -edit -l (localizedPanelLabel(\\\"Persp View\\\")) -mbv $menusOkayInPanels  $panelName;\\n$editorName = $panelName;\\nmodelEditor -e \\n    -cam `findStartUpCamera persp` \\n    -useInteractiveMode 0\\n    -displayLights \\\"default\\\" \\n    -displayAppearance \\\"smoothShaded\\\" \\n    -activeOnly 0\\n    -ignorePanZoom 0\\n    -wireframeOnShaded 0\\n    -headsUpDisplay 1\\n    -holdOuts 1\\n    -selectionHiliteDisplay 1\\n    -useDefaultMaterial 0\\n    -bufferMode \\\"double\\\" \\n    -twoSidedLighting 0\\n    -backfaceCulling 0\\n    -xray 0\\n    -jointXray 0\\n    -activeComponentsXray 0\\n    -displayTextures 0\\n    -smoothWireframe 0\\n    -lineWidth 1\\n    -textureAnisotropic 0\\n    -textureHilight 1\\n    -textureSampling 2\\n    -textureDisplay \\\"modulate\\\" \\n    -textureMaxSize 32768\\n    -fogging 0\\n    -fogSource \\\"fragment\\\" \\n    -fogMode \\\"linear\\\" \\n    -fogStart 0\\n    -fogEnd 100\\n    -fogDensity 0.1\\n    -fogColor 0.5 0.5 0.5 1 \\n    -depthOfFieldPreview 1\\n    -maxConstantTransparency 1\\n    -rendererName \\\"vp2Renderer\\\" \\n    -objectFilterShowInHUD 1\\n    -isFiltered 0\\n    -colorResolution 256 256 \\n    -bumpResolution 512 512 \\n    -textureCompression 0\\n    -transparencyAlgorithm \\\"frontAndBackCull\\\" \\n    -transpInShadows 0\\n    -cullingOverride \\\"none\\\" \\n    -lowQualityLighting 0\\n    -maximumNumHardwareLights 1\\n    -occlusionCulling 0\\n    -shadingModel 0\\n    -useBaseRenderer 0\\n    -useReducedRenderer 0\\n    -smallObjectCulling 0\\n    -smallObjectThreshold -1 \\n    -interactiveDisableShadows 0\\n    -interactiveBackFaceCull 0\\n    -sortTransparent 1\\n    -controllers 1\\n    -nurbsCurves 1\\n    -nurbsSurfaces 1\\n    -polymeshes 1\\n    -subdivSurfaces 1\\n    -planes 1\\n    -lights 1\\n    -cameras 1\\n    -controlVertices 1\\n    -hulls 1\\n    -grid 1\\n    -imagePlane 1\\n    -joints 1\\n    -ikHandles 1\\n    -deformers 1\\n    -dynamics 1\\n    -particleInstancers 1\\n    -fluids 1\\n    -hairSystems 1\\n    -follicles 1\\n    -nCloths 1\\n    -nParticles 1\\n    -nRigids 1\\n    -dynamicConstraints 1\\n    -locators 1\\n    -manipulators 1\\n    -pluginShapes 1\\n    -dimensions 1\\n    -handles 1\\n    -pivots 1\\n    -textures 1\\n    -strokes 1\\n    -motionTrails 1\\n    -clipGhosts 1\\n    -greasePencils 1\\n    -shadows 0\\n    -captureSequenceNumber -1\\n    -width 1600\\n    -height 811\\n    -sceneRenderFilter 0\\n    $editorName;\\nmodelEditor -e -viewSelected 0 $editorName;\\nmodelEditor -e \\n    -pluginObjects \\\"gpuCacheDisplayFilter\\\" 1 \\n    $editorName\"\n"
		+ "\t\t\t\t$configName;\n\n            setNamedPanelLayout (localizedPanelLabel(\"Current Layout\"));\n        }\n\n        panelHistory -e -clear mainPanelHistory;\n        sceneUIReplacement -clear;\n\t}\n\n\ngrid -spacing 5 -size 12 -divisions 5 -displayAxes yes -displayGridLines yes -displayDivisionLines yes -displayPerspectiveLabels no -displayOrthographicLabels no -displayAxesBold yes -perspectiveLabelPosition axis -orthographicLabelPosition edge;\nviewManip -drawCompass 0 -compassAngle 0 -frontParameters \"\" -homeParameters \"\" -selectionLockParameters \"\";\n}\n");
	setAttr ".st" 3;
createNode script -n "sceneConfigurationScriptNode";
	rename -uid "CBA9FC91-44E1-35E5-4F6E-17B5B054CBFE";
	setAttr ".b" -type "string" "playbackOptions -min 1 -max 120 -ast 1 -aet 200 ";
	setAttr ".st" 6;
select -ne :time1;
	setAttr ".o" 1;
	setAttr ".unw" 1;
select -ne :hardwareRenderingGlobals;
	setAttr ".otfna" -type "stringArray" 22 "NURBS Curves" "NURBS Surfaces" "Polygons" "Subdiv Surface" "Particles" "Particle Instance" "Fluids" "Strokes" "Image Planes" "UI" "Lights" "Cameras" "Locators" "Joints" "IK Handles" "Deformers" "Motion Trails" "Components" "Hair Systems" "Follicles" "Misc. UI" "Ornaments"  ;
	setAttr ".otfva" -type "Int32Array" 22 0 1 1 1 1 1
		 1 1 1 0 0 0 0 0 0 0 0 0
		 0 0 0 0 ;
	setAttr ".fprt" yes;
select -ne :renderPartition;
	setAttr -s 2 ".st";
select -ne :renderGlobalsList1;
select -ne :defaultShaderList1;
	setAttr -s 5 ".s";
select -ne :postProcessList1;
	setAttr -s 2 ".p";
select -ne :defaultRenderingList1;
select -ne :initialShadingGroup;
	setAttr -s 4 ".dsm";
	setAttr ".ro" yes;
select -ne :initialParticleSE;
	setAttr ".ro" yes;
select -ne :defaultRenderGlobals;
	addAttr -ci true -h true -sn "dss" -ln "defaultSurfaceShader" -dt "string";
	setAttr ".ren" -type "string" "arnold";
	setAttr ".dss" -type "string" "lambert1";
select -ne :defaultResolution;
	setAttr ".pa" 1;
select -ne :hardwareRenderGlobals;
	setAttr ".ctrs" 256;
	setAttr ".btrs" 512;
select -ne :ikSystem;
	setAttr -s 4 ".sol";
relationship "link" ":lightLinker1" ":initialShadingGroup.message" ":defaultLightSet.message";
relationship "link" ":lightLinker1" ":initialParticleSE.message" ":defaultLightSet.message";
relationship "shadowLink" ":lightLinker1" ":initialShadingGroup.message" ":defaultLightSet.message";
relationship "shadowLink" ":lightLinker1" ":initialParticleSE.message" ":defaultLightSet.message";
connectAttr "layerManager.dli[0]" "defaultLayer.id";
connectAttr "renderLayerManager.rlmi[0]" "defaultRenderLayer.rlid";
connectAttr "defaultRenderLayer.msg" ":defaultRenderingList1.r" -na;
connectAttr "crystalShape.iog" ":initialShadingGroup.dsm" -na;
connectAttr "ringShape2.iog" ":initialShadingGroup.dsm" -na;
connectAttr "ringShape3.iog" ":initialShadingGroup.dsm" -na;
connectAttr "ringShape1.iog" ":initialShadingGroup.dsm" -na;
// End of crystal_matrix.ma
