package org.quest.textquest;

import java.io.*;
import java.util.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.quest.textquest.entity.Answer;
import org.quest.textquest.entity.Question;
import org.quest.textquest.service.QuestService;

@WebServlet("/quest")
public class QuestServlet extends HttpServlet {
    QuestService questService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        questService = new QuestService();
        questService.startQuest();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        String sessionId = session.getId();

        request.getSession().setAttribute("username", request.getParameter("username"));
        request.getSession().setAttribute("sessionCreateTime", new Date(session.getCreationTime()));
        request.getSession().setAttribute("sessionId", sessionId);

        Question currentQuestion = null;
        String username = null;

        Cookie[] cookies = request.getCookies();
        String currentQuestionId = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if("username".equals(cookie.getName())) {
                    username = cookie.getValue();
                }
                if ("currentQuestionId".equals(cookie.getName())) {
                    currentQuestionId = cookie.getValue();
                    currentQuestion = questService.getQuestionService().loadQuestionById(Long.valueOf(currentQuestionId));
                    break;
                }
            }
        }

        if (currentQuestion == null && request.getParameter("questionId") != null) {
            String questionIdStr = request.getParameter("questionId");
            try {
                currentQuestion = questService.getQuestionService().loadQuestionById(Long.parseLong(questionIdStr));
            } catch (NumberFormatException e) {
                System.out.println("Invalid questionId parameter: " + questionIdStr);
            }
        }

        if (username != null) {
            request.setAttribute("username", username);
        }

        if (currentQuestion != null) {
            Cookie questionCookie = new Cookie("currentQuestionId", String.valueOf(currentQuestion.getId()));
            questionCookie.setMaxAge(60 * 60 * 24);  // Время жизни куки - 1 день
            response.addCookie(questionCookie);

            request.setAttribute("question", currentQuestion);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/quest.jsp");
            requestDispatcher.forward(request, response);
        } else {
            System.out.println("No current question found");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Question currentQuestion = questService.getQuestionService().loadQuestionById(Long.parseLong(req.getParameter("questionId")));

        Cookie questionCookie = new Cookie("currentQuestionId", req.getParameter("questionId"));
        questionCookie.setMaxAge(60 * 60 * 24);
        resp.addCookie(questionCookie);

        Long selectedAnswerId = Long.parseLong(req.getParameter("selectedAnswer"));
        Answer selectedAnswer = currentQuestion.getAnswers().stream()
                .filter(answer -> answer.getId().equals(selectedAnswerId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid answer ID"));

        req.setAttribute("reasonIfKnightDies", selectedAnswer.getReasonIfKnightDies());
        Question nextQuestion = questService.getQuestionService().loadQuestionById(selectedAnswer.getNextQuestionId());

        req.setAttribute("question", nextQuestion);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/quest.jsp");
        requestDispatcher.forward(req, resp);
    }
}