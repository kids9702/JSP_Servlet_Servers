package controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Controller {
	void Execute(HttpServletRequest req,HttpServletResponse resp);
}
