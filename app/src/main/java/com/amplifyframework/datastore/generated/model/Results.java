package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.HasMany;
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

/** This is an auto generated class representing the Results type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Results", type = Model.Type.USER, version = 1, authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class Results implements Model {
  public static final QueryField ID = field("Results", "id");
  public static final QueryField SCORE = field("Results", "score");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="Float") Double score;
  private final @ModelField(targetType="Questions") @HasMany(associatedWith = "resultsID", type = Questions.class) List<Questions> QuestionsResults = null;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String resolveIdentifier() {
    return id;
  }
  
  public String getId() {
      return id;
  }
  
  public Double getScore() {
      return score;
  }
  
  public List<Questions> getQuestionsResults() {
      return QuestionsResults;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Results(String id, Double score) {
    this.id = id;
    this.score = score;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Results results = (Results) obj;
      return ObjectsCompat.equals(getId(), results.getId()) &&
              ObjectsCompat.equals(getScore(), results.getScore()) &&
              ObjectsCompat.equals(getCreatedAt(), results.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), results.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getScore())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Results {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("score=" + String.valueOf(getScore()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static BuildStep builder() {
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
  public static Results justId(String id) {
    return new Results(
      id,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      score);
  }
  public interface BuildStep {
    Results build();
    BuildStep id(String id);
    BuildStep score(Double score);
  }
  

  public static class Builder implements BuildStep {
    private String id;
    private Double score;
    @Override
     public Results build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Results(
          id,
          score);
    }
    
    @Override
     public BuildStep score(Double score) {
        this.score = score;
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
    private CopyOfBuilder(String id, Double score) {
      super.id(id);
      super.score(score);
    }
    
    @Override
     public CopyOfBuilder score(Double score) {
      return (CopyOfBuilder) super.score(score);
    }
  }
  
}
