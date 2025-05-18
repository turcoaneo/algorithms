package org.toptal.test.copilot.fundamentals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;


@SpringBootTest
public class LeetcodeTests {
    @Autowired
    org.toptal.test.copilot.leet.total.chars.Solution totalCharsInTransformation;
    @Autowired
    org.toptal.test.copilot.leet.total.dna.Solution repeatedDNASequence;
    @Autowired
    org.toptal.test.copilot.leet.total.squareful.Solution numberOfSquarefulArrays;
    @Autowired
    org.toptal.test.copilot.leet.total.squareful.SquarefulArrays squarefulArrays;

    @Test
    void testDfsTwo() {
        int[] nodes = new int[]{2, 2};
        int actual = squarefulArrays.numSquarefulPerms(nodes);
        Assertions.assertEquals(1, actual);

        nodes = new int[]{2};
        actual = squarefulArrays.numSquarefulPerms(nodes);
        Assertions.assertEquals(0, actual);
    }

    @Test
    void testDfsFour() {
        int[] nodes = new int[]{2, 2, 14, 11};
        int[][] G = new int[][]{
                {0, 1, 1, 0},
                {1, 0, 1, 0},
                {1, 1, 0, 1},
                {0, 0, 1, 0},
        };
        List<Integer> neighbors;
        neighbors = new ArrayList<>();
        squarefulArrays.traverseDFSByNode(G, 1, 0, neighbors);
        Assertions.assertEquals(4, neighbors.size());

        neighbors = new ArrayList<>();
        squarefulArrays.traverseDFSByNode(G, 0, 2, neighbors);
        Assertions.assertNotEquals(4, neighbors.size());

        neighbors = new ArrayList<>();
        squarefulArrays.traverseDFSByNode(G, 2, 3, neighbors);
        Assertions.assertEquals(4, neighbors.size());

        Set<List<Integer>> setList = squarefulArrays.traverseDFS(G, nodes);
        Assertions.assertEquals(2, setList.size());
    }

    @Test
    void testDfsOne() {
        int[] nodes = new int[]{2, 2, 2, 2};
        int[][] G = new int[][]{
                {0, 1, 1, 1},
                {1, 0, 1, 1},
                {1, 1, 0, 1},
                {1, 1, 1, 0},
        };
        List<Integer> neighbors;
        neighbors = new ArrayList<>();
        squarefulArrays.traverseDFSByNode(G, 1, 0, neighbors);
        Assertions.assertEquals(4, neighbors.size());

        neighbors = new ArrayList<>();
        squarefulArrays.traverseDFSByNode(G, 2, 0, neighbors);
        Assertions.assertEquals(4, neighbors.size());

        neighbors = new ArrayList<>();
        squarefulArrays.traverseDFSByNode(G, 0, 3, neighbors);
        Assertions.assertEquals(4, neighbors.size());

        Set<List<Integer>> setList = squarefulArrays.traverseDFS(G, nodes);
        Assertions.assertEquals(1, setList.size());
    }

    @Test
    void testDfsOne2() {
        int[] nodes = new int[]{1,8,17};
        int[][] G = new int[][]{
                {0, 1, 0},
                {1, 0, 1},
                {0, 1, 0},
        };
        List<Integer> neighbors;
        neighbors = new ArrayList<>();
        squarefulArrays.traverseDFSByNode(G, 1, 0, neighbors);
        Assertions.assertEquals(3, neighbors.size());

        neighbors = new ArrayList<>();
        squarefulArrays.traverseDFSByNode(G, 2, 1, neighbors);
        Assertions.assertEquals(1, neighbors.size());

        neighbors = new ArrayList<>();
        squarefulArrays.traverseDFSByNode(G, 1, 2, neighbors);
        Assertions.assertEquals(3, neighbors.size());

        neighbors = new ArrayList<>();
        squarefulArrays.traverseDFSByNode(G, 0, 1, neighbors);
        Assertions.assertEquals(1, neighbors.size());

        Set<List<Integer>> setList = squarefulArrays.traverseDFS(G, nodes);
        Assertions.assertEquals(2, setList.size());
    }

    @Test
    void testSquarefulDfs() {
        int[] nodes = new int[]{1,17,8};

        int actual = squarefulArrays.numSquarefulPerms(nodes);
        Assertions.assertEquals(1, actual);
    }

    @Test
    void testSquareful() {
        int result = numberOfSquarefulArrays.numSquarefulPerms(new int[]{1, 17, 8});
        Assertions.assertEquals(2, result);

        result = numberOfSquarefulArrays.numSquarefulPerms(new int[]{2, 2, 2});
        Assertions.assertEquals(1, result);

        result = numberOfSquarefulArrays.numSquarefulPerms(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12});
        Assertions.assertEquals(0, result);
    }

    @Test
    void testDNA() {
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        List<String> expected = Arrays.asList("AAAAACCCCC", "CCCCCAAAAA");
        List<String> result = repeatedDNASequence.findRepeatedDnaSequencesClassic(s);
        Assertions.assertEquals(expected, result);
        result = repeatedDNASequence.findRepeatedDnaSequencesRolling(s);
        Assertions.assertEquals(expected, result);
        result = repeatedDNASequence.findRepeatedDnaSequencesBit(s);
        Assertions.assertEquals(expected, result);
        result = repeatedDNASequence.findRepeatedDnaSequencesTrie(s);
        Assertions.assertEquals(expected, result);

        s = "AAAAAAAAAAAAA";
        expected = List.of("AAAAAAAAAA");
        result = repeatedDNASequence.findRepeatedDnaSequencesClassic(s);
        Assertions.assertEquals(expected, result);
        result = repeatedDNASequence.findRepeatedDnaSequencesRolling(s);
        Assertions.assertEquals(expected, result);
        result = repeatedDNASequence.findRepeatedDnaSequencesBit(s);
        Assertions.assertEquals(expected, result);
        result = repeatedDNASequence.findRepeatedDnaSequencesTrie(s);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testTransformationNew() {
        String s = "bvqbowlhpfhpaddcegzxiawnprkhbvqlmqegsydbykdrxywxvtjlqkuvzasrfdwgepgkbcsxebrkeegttxugleyzytnfpsjimuxqjpjgqbxtbrpntxgxaahldwwnemzwtpgnbvhqikibmqwkxjlvklnuidgwhrxdnwjzxgazfckirtzzwacsvinisjzjyaibwamcbjkcxkdzripdrgyeewkpgofezpcbjbpbdhzltzmqffaqrqcefjwyuyimzpftumzdkazbkijezidrcabvfiltkxzzyywxogsccxkmihqwmehuicpolzxxqvyjdtsuohwthzmigprfjooframjuckvqxjboowpmbdwokzprniwejbbbzwvspufgilhylgukfqgdkiuezvfhxsbpkorbouyqapbwlbezukjhbiiykncopskkzajggslrplccqqcnogvhzjprfyuanmuuwhbndkmasesrpgwdgjyvcscmtiwyhsexvahitchwswafbftffrfpnwepijdmxlzihsnszpjvztxjvxbcbepbfhzcvmxfdibhycvzmzsnqtowvnyqoprmlpfdemyxawlahnlyihrkauraudjvkbsovqhpfvuxletqienvlcwnwdntusvwrgmlaibacdmxwarakxiqiqaaihtnfdmkrtknqrhhsebeizjwrdavzldafamwkjvtavitzoyzwavorvcbclapuzeeigndagdbpbxnexumoshtbkzlhsqetyzqfhswuxnxtrmzjaxlzjsdmdhgpjtdoogehkcanyylfsgpceosbeokdzarlogagljrzadttgwaksswbakodwjtpysrhthgmlpjixcxrfgaqcnqlchanwivxngtorsrplqmrxmaqwairjmbepggtuajfmqtbrcgczlogwkrjzhiqdbqbrcrlpovgspnmuxdqewqlbhzchfxrcxblhoozxmasnmzelzzfclsklpxexzmnenoigazshmhwkfrlkbkyerupbgedosfmvdburuqfydstnjyjpmusnfokoyjvdgnphvotckwvageasfrsknnxnbvmqqlvtyvbgijndosppsfrbzmkkgzzufzecmyqnacfyuylrewbuybecszxxovsflslzbbpapzywgahkqohvlzdgeveoqjvawwehecdlgtnseubafazduxgwbxqzponpizvsjosjddwajtpvenwbsgtqtessiacspjiehlqvjhugextkjlhgfkqiseqjhfvkewugmmwksrlxrvnrmstlklaytbqwfvzaebkbwjsqfgvwdoorepxcxbelvaobjgtdjlvqyninoehfroviegdyiilrpewvgpdipfqlizzxzzfvhqizrdcgmpbjffljpzyljupwenvybbvcgwvgqnfcfdfjlckioxkwnqiurpodtduitawsinvtbfnhbmozwukjqapeewivqmtjuqtlwygzfujfovvabmwsmmousfvcmoedejhurbxrzbdrreqymtuqzyjojjncibhelurkwajdfvdmeiabxfaqqpiqishborzzkztsfskfbbqwwathksdbrvzfbncxkmretflmddtoyxthwibtfeeowiippwoccnmpcswoqstmjdesxkhriqhftdojbsghfudrutzgduxdnrrheksjdyzrdtbkdbcrjkfhlfkvuadhiqbpnqljrqprdwmthcgstakkrmhivuirdvmwqkswjgnmqcqziwqbqxhrhhbtgagbdibjilhzuhglolxwpfywltxqfvbbvkmgpasvxaforlbgkttrnmhszehynywnwbxkshrajhymvgexcsqcfmnhiyikmzkwxtwaebajwoxeqmehyqrxyairhlsbazvkdbqhebtovualatufvauqeczcrrkyletfobzjvcauvnunnzogkdznfwstrkoicafynnbbdlutkeauqwyhqrdkgtrdbngeljgdfrvfiiampsuawxpsoosedtnnjwtdkwoykbukipvsvyefwkmlkrezsglrjnvafyyvbirnjkqgcobuhnwumbjwuyuoluyjmihghzlbrkkjewnobcuwopkdvokmqqwkpesgkgqowcxglywloojdjfhzeoihselaabpghovbwgaxwpdonuthapdunjpqldjkzovybxpzomvviutgljkpbdimbmanzyyzghlnwohqpjxupzrrhcrqzqhiupkinhjoniwoyxlgadvdlqnfwjucdcvvzmezbsqtcotbrmmjtlaihilpccrpszratqghwktpzciqexyqrbxnzfjbevsucqhmaaaedekipvhlbpscrwjwkzyodhssywlikjfmdbceehcvrhkdjxtvgtqmomaraninjzhvwzdteirgubbpochqocexfqxnqmqngezpedgpsiltsramrukxshrfhnuvsxafjfgkmqyapytiaeujczucwceyecjjbgbdmoavohqovlsunwbglroncvekqqbkuijehjbnyteoaegycbygfkfekkqnqcrktbbaksrcgyqmqnrttmhpefhzlmvoajypyrdchmyibgfbjgumrdfchliqdjjmoztvchtdzckjyzwyvfvabscxgymxosfstoagmvpvdofemlfzfkvexwrphbfxfsgumjvkisvvrpjztcxuhzqjwoatzqyiwjsvoismgadkxnejpbzhdymjbgejoboelmdpspqisukmzdytpqzbzpdhtyruutllgsipecruztupxjxnpgbcgtbhhkwcpcscjeebjzaxambflggzdhaupwjsswizkabaapecazfohbesowsyttexkqlayvnghvpcrhglqioufwxdhyqiyeedfioepddqmqzgvbvnkdbkkjujwrlfuxjfbuhicxnanjghbtnfdfxrcxdrqadmbpascwccifcpubjarrnzwtpglzowwekotstiwxlzyiesiqtbhoelbugxricrngzpxdarztndqnwtztoexcfsfffybkztsiwzarvzxesxrioahfheolpqneizdobicukwymgajilvdbxjbzzzqrjcoihgjlofofkxsbfmztousxolyfynxttrfhurvacvuqmvdmvdpvjefvwnmvcjqfleifbgntlwhynrqprngfcahzayyxrpunwtqmzczmigqpmmfyvvqciskpjxmimbdtttzfimioenelferxtcudmalkbqojuyrptrcrcgokjzromzeeyuznjprormpqtkjrzuwncumgrtpruqpvuiaseftkzhqdcywcovjytkvxkutknlymoiwknogudokqmwvmkqcxbyafpnmderopxihtuvtacnmkshhskozpysvfinvkseyhhdycmvcqmxaqbhbvzsdzlguqquqcabykcsqaeefmvgfmevsnukwrytcdxnmvpbfqekcjmccqfqswmotyxeezypgarndmalaagviyyenfruxcnwqtgyxifstfgbsdmlwtxmkoahobzemdrbwccufimsktsaluysyigwaveuezhfuqniciriejhwrgdzuvtducvkydelsfthgxizobpfciquywfqhhpsthfipenbotvizyqrvtfhkzvepscmfauhctmkhhyjjrmusjdwfuynghbwaorifkffnetqehbkapoqojkkubfxoisdouzqxejadzulqfquzmlaretcrrcjscjklfufuizrtvosthcecivbogxllpxzoihiirvderqkxhekqxaelgvippqonvwsifzoytfennzidgwmrppdlwwfcvuszmsmmqsftuhnauohniuftzbabhwdekllsszmloqjosdmptpuusnnzkrrxdyccjoxyoavkgdwbwkliaotzjlesbiahathesagnlrfezqorgyukasxnlnqlmcfokqztpcczlsvytwmrphmceyqtnvntwmmljfisyiszhacfyhrikvbkejblryhfgdwumwzstpzcgptphqmqqnfttuqjshcmmzfkopbqalbxjrscbefblkuhbzmnodgajzoqnkhzwsfhpvgokcyaqqybcwbqyrpdzqdtfiayvophivvfwgkigcqttxpigpqxvyvtfkmdgsdijxihcyiykdhxiyepcoxkpjirsdeezsjlogbguvshcasuvvpyrybcuvqbqtaddveyndsnrjdffszzhvknbsrcaeouyfqggpxzutgoxmkanvzkztnelncpxsjcimfoefqivpqrfvkgitwwwqtqisozoxuxheaumhntikfzlgvojnecympqdnbeghyktnzbbpcpfdqfnyihjchqtmgouwoevkylhzyxmmkoiywopdwfueznukqgxdxvgvqlxmptdszochifankbozlktpmstvllvkqytxywteiidxtvpdwurzuosonvmxjqgjteqmclsjxklopeiysjqjlkvabdqtmuvlalchllobcjxcxeicnvmgttkhxabkfxppktmptczytukbzmwymwmchawbohkqtajgiwbmlhvivmhdeeonngvcbgcsppbxeiooilgelxxuudkjxzzugcletjdtznqpzdalpzfhqzyrntljbnsbfpeeqvgpsrykunsmjjasnxmvmavuemnnvxcobjzvfvygzbdzyzrgxpqvarkwtlsbuiksjgsbhcneytfylsrokpcgjahljzfwusmlwydfhvqbjwdckzowqdhtgmtswsemghxhwlycaheudikfjfmpnuvfxrhfjjyeregrejcfcibtjjxnsyeohuhurrxyakklddbbruzxmjmbyhyrzqcjdazkvjdhebkfchywyqevqgpkwrfiwtssqvbzyovhaagqeekjcbzznziyymrlpbzclvttzwcpesswxvwknzpqdnzcfwwdanrvsgpfxakgchrkkguerurekcumuhlhoxhcddsvmxpnmkfddjcepsjnlxgqaqtmllpindndwwavcthnujkzfdkctnomipuctconyajkfvpwjjcwprzrzpjetmjwgumaxlriijkuanmqpuqlfofjdqxizbpdmphnavmsibtwsgpgtzqlxcldejedwigopiahgttqffdgyigxmmoinxjwsuljhtsjaifvcapgoxazoondpyvrdfodfqkuckyibcxykcxrzoxdxkwgjuafyshxaaofjxnftgjlswgtkbzyxxgawhrgliabdncnsfvmcuszkgtiogafcvtpssiqctlmaccvkqroobmnmirmtwhgcwxxuxghorsjauqkxjewjdbfamcgkakjlsryvawhsniuksmicjscbgcliccjrinjwtnghnecdpgjbkscuzrcvvdgigdrqmqocgwfxmosbpjwbjjafdnovgxhetphpydyogxuqyxxxqmyentbagcgfkufrbzqmzdljfhqxfdlrhvnavpouoeltrggaqvxaegboljjcladscdiddqihbzukbfcjgqahxlzvweqkokbpffhalieatflejpxstoseqossdrsbflrjolsksrboyewyxkvyodykugxinhgpcmqnzhlymvjmiybncvsvpvzldlytvhtqzocqugzvlrsuxnrkscscfvkpplovcikigyopogwojvwnxhguzxzrqjyduwpsojsimhogzhfmstjjyvsdnycpkjgynelbyszwddycgypflqkkobkyvxeccfcgixhoifxuehagwdkzztanhdivlsntppgcdajyyuhrtipsduanwkagmhcteousrweavrwqnncmxgdghjpcwjtaokhudyzxrxhnqwslgpoemjopsbjmuvtmfbyniuxskwuzdadvwueiohacxmfuddkkttddrqiiwsujeaeodbzguuddyzwnpycfknuojjdwayqyttpbsuqlyvbrgidmpzjnqbkagofrijikrittaxtiqqbwvtrnqutmzjfnqkashbdtisnfffihniknqeblchemzwsfhjoiotjmuolyprdfpidfpeutllwtavcdoreitatpdjififpfobnrhkowzjnbdoselynbewxagtzbetyriooufnpgjnzirjutvestaxbudtfwtlmhokedkuntejcyyzmhyeveyixyahwwmohmtuwvosqiiwokkyyiqdfiuqrylczllgbrmjpydskjblztygxgpeqfiikbsvrrmfmjjyjnmmcshmkhywnztmymcrsgpqmcvlypuydtrnyjxrlnjzkcwlzzvougctjsnzrdhqakyftwbybljakdsfmsexaxaxksvbqqvaegfestqfdsoaofaggyfhvkvhlvcbgtwdmnuwthivinencppqwxaojabpydxlcfblgysglnjoqkzywfwetjjshordjpfxawoupqpjtirmzylmpleuonnveqdtwzekqakrobsxbxqwildpuvkmlogtzzhwvuoztkspxekuvrqwzbzdzhjozqosnvmrucnspeorbrwlgbrqljqgmkuedgcjptzykyzmovpungwinhmrysehreebjtkafbglmrauhympjkarcyyldmzhkvjnkrtzdhhvtqoywsrbfiwswwzapmkbyewkbcujbyyxifbzigxsfoteelpywbjdfzvcnuwybbgndnbuenozmlpawibxklhgmcoodwipjettuhutambkyevapbhhumjhqfakarskfmjjpigrjsxlqhixovzdefqxymgnwcovfifjvubjaxoxtqmaidhtaqcsqggwxwjcsqqhlqysswpezaoarjssligvnccvbuejjbkwzxaikkcsrqmrdvmskgopzqlpxdouzuhsokcbweuwrzqcuqjxewhpmkrafivzurajyzlgsstlssdhfnjmjotyjqbnsjnfcvsptejrzicswkzvmhwumtprzhdxzqxjlnkgmociveqqrngydipsghrmntqymmqmwxrlovgxtepxfxlojwpmlzmenslyljtptkndyrjuikjrrurazgwgjginlfyuwmkogriokpxhbablkdwwnxxpjokfnlimmbeausuukteotoypwakqzeborvfwiolyvvxniukzwfzvubuvayzkhldwmbzpwjzmooryyxotosaaaecoivyqvqzqrylfdugcubmasktfpxpsvkqrqntipxlqpbjgpjootfvxbycvdolnrmcgvdkyjlyqjgozirijcwafdajnrokkubafhujulkfmqsewbllzqhkutsardfvkhswcpflausmcgjlhgskdsoppylvgulxvuquaehvssuoxfgqvidlmmocmbuhmzpmetmoewsokxpcnybnrmkpjxgebuydnftz";
        int t = 50_000_000;
        List<Integer> nums = Arrays.asList(23, 20, 4, 11, 4, 24, 13, 25, 12, 21, 17, 7, 6, 21, 12, 11, 22, 25, 22, 16, 19, 8, 16, 18, 19, 16);
        int expected = totalCharsInTransformation.lengthAfterTransformationsString(s, t, nums);
        System.out.println("Expected: " + expected);
        int result = totalCharsInTransformation.lengthAfterTransformations(s, t, nums);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testTransformationNewMulti() {
        String s = "abcdefghijklmnopqrstuvwxy";
        int t = 100_000;
        List<Integer> nums = Arrays.asList(1, 1, 4, 3, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5, 9, 25);
        int expected = totalCharsInTransformation.lengthAfterTransformationsString(s, t, nums);
        System.out.println("Expected: " + expected);
        int result = totalCharsInTransformation.lengthAfterTransformations(s, t, nums);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testTransformation() {
        String s = "azbk";
        int t = 1;
        List<Integer> nums = Arrays.asList(2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2);
        int expected = totalCharsInTransformation.lengthAfterTransformationsString(s, t, nums);
        System.out.println("Expected: " + expected);
        int result = totalCharsInTransformation.lengthAfterTransformations(s, t, nums);
        Assertions.assertEquals(expected, result);
    }


    @Test
    void testMatrixSimple() {
        String s = "abcyy";
        int t = 3;
        List<Integer> nums = Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2);
        int expected = totalCharsInTransformation.lengthAfterTransformationsString(s, t, nums);
        System.out.println("Expected: " + expected);
        int result = totalCharsInTransformation.lengthAfterTransformations(s, t, nums);
        Assertions.assertEquals(expected, result);
    }
}