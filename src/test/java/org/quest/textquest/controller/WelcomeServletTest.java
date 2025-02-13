package org.quest.textquest.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.Mockito.*;

class WelcomeServletTest {

    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private HttpSession session;
    @Mock private RequestDispatcher requestDispatcher;

    @InjectMocks
    private WelcomeServlet welcomeServlet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    //Testing steps:
    //1. Set mocks for req, resp, session and reqDispatcher +
    //2. Set returns for getSession() and getRequestDispatcher("index.jsp") +
    //4. Use doGet method +
    //5 checking testUsername inside of session
    //6. Checking expected amount of usages getSession(), getRequestDispatcher("index.jsp")
    // and forward (req, resp) methods
    //7 Checking cookie settings
    @Test
    void testDoGet() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/quest?questionId=1")).thenReturn(requestDispatcher);
        when(request.getParameter("username")).thenReturn("TestUser");

        welcomeServlet.doGet(request, response);

        verify(request, times(1)).getSession();
        verify(request, times(1)).getRequestDispatcher("/quest?questionId=1");

        verify(session).setAttribute("username", "TestUser");
        verify(response).addCookie(argThat(cookie ->
                cookie.getName().equals("username") &&
                        cookie.getValue().equals("TestUser") &&
                        cookie.getMaxAge() == 60 * 60 * 24
        ));

        verify(requestDispatcher).forward(request, response);
    }
}