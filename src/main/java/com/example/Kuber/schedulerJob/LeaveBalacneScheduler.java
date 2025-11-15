package com.example.Kuber.schedulerJob;

import com.example.Kuber.model.LeaveBalance;
import com.example.Kuber.repository.LeaveBalanceRepository;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LeaveBalacneScheduler {

    private final LeaveBalanceRepository leaveBalanceRepository;

    public LeaveBalacneScheduler(LeaveBalanceRepository leaveBalanceRepository) {
        this.leaveBalanceRepository = leaveBalanceRepository;
    }
    //runs every year on Dec 31 at 23:59
    @Scheduled(cron = "0 59 23 31 12 *")

    public void scheduledLeaveBalance(){

        List<LeaveBalance> leaveBalances = leaveBalanceRepository.findAll();

        for (LeaveBalance balance : leaveBalances){

            Long current = balance.getCurrentBalance() == null ? 0: balance.getCurrentBalance();
            Long carryoverlimit = 5L;

            Long carryOver = Math.min(current,carryoverlimit);
            Long expired = current - carryOver;

            balance.setCarryOverBalance(carryOver);
            balance.setExpiredLeave(expired);

            balance.setPreviousBalance(carryOver);
            balance.setCurrentBalance(balance.getLeaveTypes().getMaxLeaves());
            balance.setTotalBalance(balance.getPreviousBalance() + balance.getCurrentBalance());
            balance.setUsedLeave(0L);
            balance.setAcceptedLeave(0L);
            balance.setRejectedLeave(0L);

            leaveBalanceRepository.save(balance);
        }
        System.out.println("LeaveBalance recreated successfully.");
    }

}
