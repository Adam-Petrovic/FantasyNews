package usecase.draft_words;

public class DraftWordsInteractor implements DraftWordsInputBoundary{
    private final DraftWordsOutputBoundary draftWordsPresenter;
    private final DraftWordsLeagueDataAccessInterface draftWordsLeagueDataAccessInterface;
    public DraftWordsInteractor(DraftWordsOutputBoundary presenter, DraftWordsLeagueDataAccessInterface DAOInterface) {
        this.draftWordsPresenter = presenter;
        this.draftWordsLeagueDataAccessInterface = DAOInterface;
    }

    public void execute(DraftWordsInputData draftWordsInputData) {
//        JSONObject leagueData = draftWordsLeagueDataAccessInterface.get();
        String name = draftWordsInputData.getUsername();
        Integer num = draftWordsInputData.getCategoryNum();
        String newWord = draftWordsInputData.getNewWord();
        String leagueID = draftWordsInputData.getLeagueID();
        String words[] = draftWordsLeagueDataAccessInterface.draftWord(name, num, newWord, leagueID);
//        JSONObject data = draftWordsLeagueDataAccessInterface.draftWord(name, num, newWord, leagueID, leagueData);
//        System.out.println(data.toString());
//        System.out.println("--------------------");
//        System.out.println(data.getJSONObject(leagueID).toString());
//        System.out.println(data.getJSONObject(leagueID).getJSONObject("data").toString());
//        System.out.println(data.getJSONObject(leagueID).getJSONObject("data").getJSONArray(name).toString());
//        JSONArray words = data.getJSONObject(leagueID).getJSONObject("data").getJSONArray(name);

//        draftWordsLeagueDataAccessInterface.save(data);

        final DraftWordsOutputData outputData = new DraftWordsOutputData(words);
        draftWordsPresenter.showDraftedWords(outputData);
    }

}

