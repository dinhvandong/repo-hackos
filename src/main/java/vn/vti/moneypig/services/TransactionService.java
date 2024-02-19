package vn.vti.moneypig.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vti.moneypig.database.SequenceGeneratorService;
import vn.vti.moneypig.dto.TransactionResponse;
import vn.vti.moneypig.models.Category;
import vn.vti.moneypig.models.CategoryGroup;
import vn.vti.moneypig.models.Transaction;
import vn.vti.moneypig.models.User;
import vn.vti.moneypig.repositories.TransactionRepository;
import vn.vti.moneypig.utils.DateUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    SequenceGeneratorService sequenceGeneratorService;
    public Transaction insert(Transaction newTransaction)
    {
        Long id  = sequenceGeneratorService.generateSequence(Transaction.SEQUENCE_NAME);
        newTransaction.setId(id);
        newTransaction.setActive(1);
        newTransaction.setCreatedDate(DateUtils.getCurrentDate());
        return transactionRepository.insert(newTransaction);
    }

    public Transaction findById(Long id)
    {
        Optional<Transaction> optionalTransaction =  transactionRepository.findById(id);
        return optionalTransaction.orElse(null);
    }
    public Transaction update(Transaction updateTransaction)
    {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(updateTransaction.getId());
        if(optionalTransaction.isEmpty())
        {
            return null;
        }
        Transaction foundTransaction = optionalTransaction.get();
        foundTransaction.setMoney(updateTransaction.getMoney());
        foundTransaction.setName(updateTransaction.getName());
        foundTransaction.setWithPerson(updateTransaction.getWithPerson());
        return transactionRepository.save(foundTransaction);
    }
    public boolean delete(long idTransaction)
    {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(idTransaction);
        if(optionalTransaction.isEmpty())
        {
            return false;
        }
        Transaction foundTransaction = optionalTransaction.get();
        foundTransaction.setActive(0);
        Transaction updateTransaction = transactionRepository.save(foundTransaction);
        return true;
    }
    @Autowired
    CategoryGroupService  categoryGroupService;
    @Autowired
    UserService userService;
    public TransactionResponse convertTransaction(Transaction origin)
    {
        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setActive(origin.getActive());
        Category category = categoryGroupService.getCategoryById(origin.getCategoryID());
        CategoryGroup group = categoryGroupService.findById(origin.getGroupID());
        User user = userService.findById(origin.getUserID());
        transactionResponse.setCategory(category.getName());
        transactionResponse.setId(origin.getId());
        transactionResponse.setMoney(origin.getMoney());
        transactionResponse.setListImages(origin.getListImages());
        transactionResponse.setCreatedDate(origin.getCreatedDate());
        transactionResponse.setNote(origin.getNote());
        transactionResponse.setUserId(origin.getUserID());
        transactionResponse.setUsername(user.getUsername());
        transactionResponse.setGroup(group.getName());
        transactionResponse.setCategoryID(origin.getCategoryID());
        transactionResponse.setGroupID(origin.getGroupID());
        transactionResponse.setWithPerson(origin.getWithPerson());
        transactionResponse.setName(origin.getName());
        return  transactionResponse;
    }
    public List<TransactionResponse> findAll()
    {
        List <Transaction > transactionList =transactionRepository.findAll();
        List<TransactionResponse> transactionResponses = new ArrayList<>();
        for(Transaction transaction: transactionList)
        {
            TransactionResponse dto = convertTransaction(transaction);
            transactionResponses.add(dto);
        }
        return transactionResponses;
    }
    public List<Transaction> findByUserID(Long userID)
    {
        return transactionRepository.findAllByUserID(userID);
    }
    public List<Transaction> findByDate(Long startDate, Long endDate, Long userID)
    {
        return transactionRepository.findTransactionsByDateAndUser(startDate, endDate, userID);
    }
    public List<Transaction> findByDateRange(Long startDate, Long endDate)
    {
        return transactionRepository.findTransactionsByDateRange(startDate, endDate);
    }
}
