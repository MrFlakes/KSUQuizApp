package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelOperation;
import com.amplifyframework.core.model.annotations.AuthRule;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Questions type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Questions", type = Model.Type.USER, version = 1, authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
@Index(name = "byResults", fields = {"resultsID"})
public final class Questions implements Model {
  public static final QueryField ID = field("Questions", "id");
  public static final QueryField QUESTION_NO = field("Questions", "QuestionNo");
  public static final QueryField QUESTION = field("Questions", "Question");
  public static final QueryField ANSWER = field("Questions", "Answer");
  public static final QueryField CORRECT_ANSWER = field("Questions", "correctAnswer");
  public static final QueryField RESULTS_ID = field("Questions", "resultsID");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="Int") Integer QuestionNo;
  private final @ModelField(targetType="String") String Question;
  private final @ModelField(targetType="String", isRequired = true) List<String> Answer;
  private final @ModelField(targetType="String") String correctAnswer;
  private final @ModelField(targetType="ID") String resultsID;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String resolveIdentifier() {
    return id;
  }
  
  public String getId() {
      return id;
  }
  
  public Integer getQuestionNo() {
      return QuestionNo;
  }
  
  public String getQuestion() {
      return Question;
  }
  
  public List<String> getAnswer() {
      return Answer;
  }
  
  public String getCorrectAnswer() {
      return correctAnswer;
  }
  
  public String getResultsId() {
      return resultsID;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Questions(String id, Integer QuestionNo, String Question, List<String> Answer, String correctAnswer, String resultsID) {
    this.id = id;
    this.QuestionNo = QuestionNo;
    this.Question = Question;
    this.Answer = Answer;
    this.correctAnswer = correctAnswer;
    this.resultsID = resultsID;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Questions questions = (Questions) obj;
      return ObjectsCompat.equals(getId(), questions.getId()) &&
              ObjectsCompat.equals(getQuestionNo(), questions.getQuestionNo()) &&
              ObjectsCompat.equals(getQuestion(), questions.getQuestion()) &&
              ObjectsCompat.equals(getAnswer(), questions.getAnswer()) &&
              ObjectsCompat.equals(getCorrectAnswer(), questions.getCorrectAnswer()) &&
              ObjectsCompat.equals(getResultsId(), questions.getResultsId()) &&
              ObjectsCompat.equals(getCreatedAt(), questions.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), questions.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getQuestionNo())
      .append(getQuestion())
      .append(getAnswer())
      .append(getCorrectAnswer())
      .append(getResultsId())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Questions {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("QuestionNo=" + String.valueOf(getQuestionNo()) + ", ")
      .append("Question=" + String.valueOf(getQuestion()) + ", ")
      .append("Answer=" + String.valueOf(getAnswer()) + ", ")
      .append("correctAnswer=" + String.valueOf(getCorrectAnswer()) + ", ")
      .append("resultsID=" + String.valueOf(getResultsId()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static AnswerStep builder() {
      return new Builder();
  }
  
  /**
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static Questions justId(String id) {
    return new Questions(
      id,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      QuestionNo,
      Question,
      Answer,
      correctAnswer,
      resultsID);
  }
  public interface AnswerStep {
    BuildStep answer(List<String> answer);
  }
  

  public interface BuildStep {
    Questions build();
    BuildStep id(String id);
    BuildStep questionNo(Integer questionNo);
    BuildStep question(String question);
    BuildStep correctAnswer(String correctAnswer);
    BuildStep resultsId(String resultsId);
  }
  

  public static class Builder implements AnswerStep, BuildStep {
    private String id;
    private List<String> Answer;
    private Integer QuestionNo;
    private String Question;
    private String correctAnswer;
    private String resultsID;
    @Override
     public Questions build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Questions(
          id,
          QuestionNo,
          Question,
          Answer,
          correctAnswer,
          resultsID);
    }
    
    @Override
     public BuildStep answer(List<String> answer) {
        Objects.requireNonNull(answer);
        this.Answer = answer;
        return this;
    }
    
    @Override
     public BuildStep questionNo(Integer questionNo) {
        this.QuestionNo = questionNo;
        return this;
    }
    
    @Override
     public BuildStep question(String question) {
        this.Question = question;
        return this;
    }
    
    @Override
     public BuildStep correctAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
        return this;
    }
    
    @Override
     public BuildStep resultsId(String resultsId) {
        this.resultsID = resultsId;
        return this;
    }
    
    /**
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, Integer questionNo, String question, List<String> answer, String correctAnswer, String resultsId) {
      super.id(id);
      super.answer(answer)
        .questionNo(questionNo)
        .question(question)
        .correctAnswer(correctAnswer)
        .resultsId(resultsId);
    }
    
    @Override
     public CopyOfBuilder answer(List<String> answer) {
      return (CopyOfBuilder) super.answer(answer);
    }
    
    @Override
     public CopyOfBuilder questionNo(Integer questionNo) {
      return (CopyOfBuilder) super.questionNo(questionNo);
    }
    
    @Override
     public CopyOfBuilder question(String question) {
      return (CopyOfBuilder) super.question(question);
    }
    
    @Override
     public CopyOfBuilder correctAnswer(String correctAnswer) {
      return (CopyOfBuilder) super.correctAnswer(correctAnswer);
    }
    
    @Override
     public CopyOfBuilder resultsId(String resultsId) {
      return (CopyOfBuilder) super.resultsId(resultsId);
    }
  }
  
}
