package com.epsi.projects.chooseyourwine.Tools;

import java.io.IOException;
import java.io.InputStream;

public class InputStreamOperations {

    /**
     * @param in : buffer with the json result
     * @param bufSize : size of the buffer
     * @return : string corresponding to the buffer
     */
    public static String InputStreamToString (InputStream in, int bufSize) {
        final StringBuilder out = new StringBuilder();
        final byte[] buffer = new byte[bufSize];
        try {
            for (int ctr; (ctr = in.read(buffer)) != -1;) {
                out.append(new String(buffer, 0, ctr));
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot convert stream to string", e);
        }

        // Return InputStream's chain
        return out.toString();
    }

    /**
     * @param in : buffer with the json result
     * @return : the string corresponding to the buffer
     */
    public static String InputStreamToString (InputStream in) {
        // Call InputStreamToString with buffer's default value
        return InputStreamToString(in, 1024);
    }
}
