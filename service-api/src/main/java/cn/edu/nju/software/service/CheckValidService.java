package cn.edu.nju.software.service;

/**
 * Created by xmc1993 on 2017/5/15.
 */
public interface CheckValidService {

    boolean isTagExist(int tagId);

    public boolean isTagExistHard(int tagId);

    /**
     * valid = 1
     *
     * @param storyId
     * @return
     */
    boolean isStoryExist(int storyId);

    /**
     * valid = 0 or 1
     *
     * @param storyId
     * @return
     */
    boolean isStoryExistHard(int storyId);

    boolean isUserExist(int userId);

    /**
     * valid = 1
     *
     * @param soundEffectId
     * @return
     */
    boolean isSoundEffectExist(int soundEffectId);

    boolean isBackgroundMusicExist(int id);

    /**
     * valid = 0 or 1
     *
     * @param soundEffectId
     * @return
     */
    boolean isSoundEffectExistHard(int soundEffectId);

    boolean isBackgroundMusicExistHard(int id);


    boolean isWorksExist(int worksId);

    boolean isWorksExistHard(int worksId);

    boolean isSoundEffectTagExist(int tagId);

    boolean isBackgroundMusicTagExist(int tagId);

    boolean isSoundEffectTagExistHard(int tagId);

    boolean isBackgroundMusicTagExistHard(int tagId);




}
