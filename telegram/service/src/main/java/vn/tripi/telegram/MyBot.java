package vn.tripi.telegram;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import vn.tripi.telegram.entity.Flight;
import vn.tripi.telegram.entity.User;
import vn.tripi.telegram.repository.impl.UserRepoImpl;

import java.util.ArrayList;
import java.util.List;

public class MyBot extends TelegramLongPollingBot {
    private UserRepoImpl userList;
    @Autowired
    public MyBot() {
        this.userList = userList;
    }
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String chatId = update.getMessage().getChatId().toString();

            // Kiểm tra xem người dùng đã tồn tại trong cơ sở dữ liệu hay chưa
            User user = userList.findByChatId(chatId);
            if (user == null) {
                // Nếu không tồn tại, thêm người dùng mới vào cơ sở dữ liệu
                String username = update.getMessage().getFrom().getUserName();
                user = new User(chatId, username);
                userList.save(user);
            }
            // Xử lý tin nhắn từ người dùng
            // Lấy tên người dùng từ tin nhắn
            String customerName = update.getMessage().getFrom().getFirstName();
            // Tìm kiếm thông tin chuyến bay dựa trên tên người dùng
            List<Flight> flights = findFlightsByCustomerName(customerName);

            // Gửi thông tin chuyến bay cho người dùng
            for (Flight flight : flights) {
                SendMessage message = new SendMessage();
                message.setChatId(chatId);
                message.setText("Thông tin chuyến bay:\n" +
                                "Điểm đi: " + flight.getDeparture() + "\n" +
                                "Điểm đến: " + flight.getDestination() + "\n" +
                                "Giờ bay: " + flight.getDepartureTime());

                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Override
    public String getBotUsername() {
        return "dulichmytourbot";
    }
    @Override
    public String getBotToken() {
        return "6007232966:AAGo9fnQHteM20vTpwx232oQI3EXVxY-guY";
    }
    private void sendBroadcastMessage(String messageText) {
        List<User> listUser = userList.findAll();
        for (User user : listUser) {
            SendMessage message = new SendMessage();
            message.setChatId(user.getChatId());
            message.setText(messageText);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
    private User getUserByChatId(String chatId) {
        List<User> listUser = userList.findAll();
        for (User user : listUser) {
            if (user.getChatId().equals(chatId)) {
                return user;
            }
        }
        return null;
    }
    private List<Flight> findFlightsByCustomerName(String customerName) {
        // Thực hiện truy vấn database để tìm kiếm chuyến bay của người dùng dựa trên tên khách hàng
        // và trả về danh sách các chuyến bay tìm thấy.
        // Ở đây, ví dụ tôi sử dụng một danh sách giả lập.
        List<Flight> flights = new ArrayList<>();
        // Thực hiện truy vấn database và lấy thông tin chuyến bay
        // gán vào danh sách flights
        return flights;
    }
}