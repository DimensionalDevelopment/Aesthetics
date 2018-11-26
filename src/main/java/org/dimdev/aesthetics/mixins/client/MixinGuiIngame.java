package org.dimdev.aesthetics.mixins.client;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.text.TextFormatting;
import org.dimdev.aesthetics.Aesthetics;
import org.dimdev.aesthetics.ModConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Collection;
import java.util.List;

@Mixin(GuiIngame.class)
public class MixinGuiIngame extends Gui {

    @Shadow public FontRenderer getFontRenderer() {
        return null;
    }

    /**
     * @author ZombieHDGaming
     */
    @Overwrite
    protected void renderScoreboard(ScoreObjective objective, ScaledResolution scaledRes) {
        if(ModConfig.sb.scoreboardLook.equals(ModConfig.ScoreBoardType.DEFAULT))
            renderDefault(objective, scaledRes);
        if(ModConfig.sb.scoreboardLook.equals(ModConfig.ScoreBoardType.SPACED_CLEAR))
            renderSpacedClear(objective, scaledRes);
        if(ModConfig.sb.scoreboardLook.equals(ModConfig.ScoreBoardType.EDGE_CLEAR))
            renderEdgeClear(objective, scaledRes);
    }

    private void renderDefault(ScoreObjective objective, ScaledResolution scaledRes) {
        Scoreboard scoreboard = objective.getScoreboard();
        Collection<Score> collection = scoreboard.getSortedScores(objective);
        List<Score> list = Lists.newArrayList(Iterables.filter(collection, Aesthetics.getPredicate()));

        if (list.size() > 15)
        {
            collection = Lists.newArrayList(Iterables.skip(list, collection.size() - 15));
        }
        else
        {
            collection = list;
        }

        int i = this.getFontRenderer().getStringWidth(objective.getDisplayName());

        for (Score score : collection) {
            ScorePlayerTeam scoreplayerteam = scoreboard.getPlayersTeam(score.getPlayerName());
            String s = ScorePlayerTeam.formatPlayerName(scoreplayerteam, score.getPlayerName()) + ": " + TextFormatting.RED + score.getScorePoints();
            i = Math.max(i, this.getFontRenderer().getStringWidth(s));
        }

        int i1 = collection.size() * this.getFontRenderer().FONT_HEIGHT;
        int j1 = scaledRes.getScaledHeight() / 2 + i1 / 3;
        int l1 = scaledRes.getScaledWidth() - i - 3;
        int j = 0;

        for (Score score1 : collection) {
            ++j;
            ScorePlayerTeam scoreplayerteam1 = scoreboard.getPlayersTeam(score1.getPlayerName());
            String s1 = ScorePlayerTeam.formatPlayerName(scoreplayerteam1, score1.getPlayerName());
            String s2 = TextFormatting.RED + "" + score1.getScorePoints();
            int k = j1 - j * this.getFontRenderer().FONT_HEIGHT;
            int l = scaledRes.getScaledWidth() - 3 + 2;
            drawRect(l1 - 2, k, l, k + this.getFontRenderer().FONT_HEIGHT, 1342177280);
            this.getFontRenderer().drawString(s1, l1, k, 553648127);
            this.getFontRenderer().drawString(s2, l - this.getFontRenderer().getStringWidth(s2), k, 553648127);

            if (j == collection.size())
            {
                String s3 = objective.getDisplayName();
                drawRect(l1 - 2, k - this.getFontRenderer().FONT_HEIGHT - 1, l, k - 1, 1610612736);
                drawRect(l1 - 2, k - 1, l, k, 1342177280);
                this.getFontRenderer().drawString(s3, l1 + i / 2 - this.getFontRenderer().getStringWidth(s3) / 2, k - this.getFontRenderer().FONT_HEIGHT, 553648127);
            }
        }
    }

    private void renderSpacedClear(ScoreObjective objective, ScaledResolution scaledRes) {
        Scoreboard scoreboard = objective.getScoreboard();
        Collection<Score> collection = scoreboard.getSortedScores(objective);
        List<Score> list = Lists.newArrayList(Iterables.filter(collection, Aesthetics.getPredicate()));

        if (list.size() > 15) {
            collection = Lists.newArrayList(Iterables.skip(list, collection.size() - 15));
        } else {
            collection = list;
        }

        int i = this.getFontRenderer().getStringWidth(objective.getDisplayName());

        for (Score score : collection) {
            ScorePlayerTeam scoreplayerteam = scoreboard.getPlayersTeam(score.getPlayerName());
            String s = ScorePlayerTeam.formatPlayerName(scoreplayerteam, score.getPlayerName()) + ": " + TextFormatting.RED + score.getScorePoints();
            i = Math.max(i, this.getFontRenderer().getStringWidth(s));
        }

        int i1 = collection.size() * this.getFontRenderer().FONT_HEIGHT;
        int j1 = scaledRes.getScaledHeight() / 2 + i1 / 3;
        int l1 = scaledRes.getScaledWidth() - i - 3;
        int j = 0;

        renderText(objective, scaledRes, scoreboard, j, j1, l1, i, collection);
    }

    private void renderEdgeClear(ScoreObjective objective, ScaledResolution scaledRes) {
        Scoreboard scoreboard = objective.getScoreboard();
        Collection<Score> collection = scoreboard.getSortedScores(objective);
        List<Score> list = Lists.newArrayList(Iterables.filter(collection, Aesthetics.getPredicate()));

        if (list.size() > 15) {
            collection = Lists.newArrayList(Iterables.skip(list, collection.size() - 15));
        } else {
            collection = list;
        }

        int i = this.getFontRenderer().getStringWidth(objective.getDisplayName());

        for (Score score : collection) {
            ScorePlayerTeam scoreplayerteam = scoreboard.getPlayersTeam(score.getPlayerName());
            String s = ScorePlayerTeam.formatPlayerName(scoreplayerteam, score.getPlayerName());
            i = Math.max(i, this.getFontRenderer().getStringWidth(s));
        }

        int i1 = collection.size() * this.getFontRenderer().FONT_HEIGHT;
        int j1 = scaledRes.getScaledHeight() / 2 + i1 / 3;
        int l1 = scaledRes.getScaledWidth() - i - 3;
        int j = 0;

        renderText(objective, scaledRes, scoreboard, j, j1, l1, i, collection);
    }

    private void renderText(ScoreObjective objective, ScaledResolution scaledRes, Scoreboard scoreboard, int j, int j1, int l1, int i, Collection<Score> collection) {
        for (Score score1 : collection) {
            ++j;
            ScorePlayerTeam scoreplayerteam1 = scoreboard.getPlayersTeam(score1.getPlayerName());
            String s1 = ScorePlayerTeam.formatPlayerName(scoreplayerteam1, score1.getPlayerName());
            int k = j1 - j * this.getFontRenderer().FONT_HEIGHT;
            this.getFontRenderer().drawString(s1, l1, k, 0xFFFFFF, true);

            if (j == collection.size()) {
                String s3 = objective.getDisplayName();
                this.getFontRenderer().drawString(s3, l1 + i / 2 - this.getFontRenderer().getStringWidth(s3) / 2, k - this.getFontRenderer().FONT_HEIGHT, 0xFFFFFF, true);
            }
        }
    }

}