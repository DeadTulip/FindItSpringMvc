package p.hh.smvc.commands;

import p.hh.smvc.domain.Team;
import p.hh.smvc.domain.User;

import java.util.List;

/**
 * Created by Atos on 12-7-2017.
 */
public class TeamCommand {
    Team team;
    List<UserItemCount> userItemCountList;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<UserItemCount> getUserItemCountList() {
        return userItemCountList;
    }

    public void setUserItemCountList(List<UserItemCount> userItemCountList) {
        this.userItemCountList = userItemCountList;
    }

    public static class UserItemCount {
        private User user;
        private Integer count;

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }
    }
}
