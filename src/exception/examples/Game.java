package exception.examples;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Game {
    private static final String FORMAT_FOR_DATES = "yyyy/MM/dd HH:mm:ss";
    private static final String FILE_NAME_SCORE = "score.txt";
    private static final String FILE_NAME_PROGRESS = "progress.txt";
    private static final String FILE_NAME_STATISTICS = "statistics.txt";

    public void saveGame() {
        try {
            saveProgress();
            saveScore();
            saveStatistics();
        }catch (SaveProgressException ex){
            System.err.println("Can't Save Progress" + ex.getMessage());
        }catch (SaveScoreException ex){
            ex.printStackTrace();
        }catch (SaveStatisticsException ex){
            System.err.println("Can't Save Statistics" + ex.getMessage());
        }
    }

    private void saveStatistics() throws SaveStatisticsException{
        try (FileWriter writer = new FileWriter(FILE_NAME_STATISTICS,true)){
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_FOR_DATES);
            writer.append("Saved: ").append(now.format(formatter)).append(System.lineSeparator());
        } catch (IOException e) {
            throw new SaveStatisticsException();
        }
    }

    private void saveScore() throws SaveScoreException{
        try (FileWriter writer = new FileWriter(FILE_NAME_SCORE,true)){
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_FOR_DATES);
            writer.append("Saved: ").append(now.format(formatter)).append(System.lineSeparator());

            throw new IOException("IO exception testing");
        } catch (IOException e) {
            throw new SaveScoreException("Error Message",e);
        }
    }

    private void saveProgress() throws SaveProgressException{
        try(FileWriter writer = new FileWriter(FILE_NAME_PROGRESS,true)) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_FOR_DATES);
            writer.append("Saved: ").append(now.format(formatter)).append(System.lineSeparator());
        } catch (IOException e) {
            throw new SaveProgressException();
        }
    }
}
