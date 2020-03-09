package com.dhaval.otpautoread.interfaces;

/**
 * Created on : March 09, 2020
 * Author     : Dhaval Jayani
 */
public interface OtpReceivedInterface {
  void onOtpReceived(String otp);
  void onOtpTimeout();
}
