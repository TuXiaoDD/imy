// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: AuthenticateRequestProto.proto

package com.lym.protobuf;

public final class AuthenticateRequestProto {
  private AuthenticateRequestProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface AuthenticateRequestOrBuilder extends
      // @@protoc_insertion_point(interface_extends:com.lym.protobuf.AuthenticateRequest)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string uid = 1;</code>
     */
    java.lang.String getUid();
    /**
     * <code>string uid = 1;</code>
     */
    com.google.protobuf.ByteString
        getUidBytes();

    /**
     * <code>string toke = 2;</code>
     */
    java.lang.String getToke();
    /**
     * <code>string toke = 2;</code>
     */
    com.google.protobuf.ByteString
        getTokeBytes();

    /**
     * <code>int64 timestamp = 3;</code>
     */
    long getTimestamp();
  }
  /**
   * Protobuf type {@code com.lym.protobuf.AuthenticateRequest}
   */
  public  static final class AuthenticateRequest extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:com.lym.protobuf.AuthenticateRequest)
      AuthenticateRequestOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use AuthenticateRequest.newBuilder() to construct.
    private AuthenticateRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    public AuthenticateRequest() {
      uid_ = "";
      toke_ = "";
      timestamp_ = 0L;
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private AuthenticateRequest(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownFieldProto3(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 10: {
              java.lang.String s = input.readStringRequireUtf8();

              uid_ = s;
              break;
            }
            case 18: {
              java.lang.String s = input.readStringRequireUtf8();

              toke_ = s;
              break;
            }
            case 24: {

              timestamp_ = input.readInt64();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.lym.protobuf.AuthenticateRequestProto.internal_static_com_lym_protobuf_AuthenticateRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.lym.protobuf.AuthenticateRequestProto.internal_static_com_lym_protobuf_AuthenticateRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.lym.protobuf.AuthenticateRequestProto.AuthenticateRequest.class, com.lym.protobuf.AuthenticateRequestProto.AuthenticateRequest.Builder.class);
    }

    public static final int UID_FIELD_NUMBER = 1;
    private volatile java.lang.Object uid_;
    /**
     * <code>string uid = 1;</code>
     */
    public java.lang.String getUid() {
      java.lang.Object ref = uid_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        uid_ = s;
        return s;
      }
    }
    /**
     * <code>string uid = 1;</code>
     */
    public com.google.protobuf.ByteString
        getUidBytes() {
      java.lang.Object ref = uid_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        uid_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int TOKE_FIELD_NUMBER = 2;
    private volatile java.lang.Object toke_;
    /**
     * <code>string toke = 2;</code>
     */
    public java.lang.String getToke() {
      java.lang.Object ref = toke_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        toke_ = s;
        return s;
      }
    }
    /**
     * <code>string toke = 2;</code>
     */
    public com.google.protobuf.ByteString
        getTokeBytes() {
      java.lang.Object ref = toke_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        toke_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int TIMESTAMP_FIELD_NUMBER = 3;
    private long timestamp_;
    /**
     * <code>int64 timestamp = 3;</code>
     */
    public long getTimestamp() {
      return timestamp_;
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (!getUidBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, uid_);
      }
      if (!getTokeBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, toke_);
      }
      if (timestamp_ != 0L) {
        output.writeInt64(3, timestamp_);
      }
      unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!getUidBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, uid_);
      }
      if (!getTokeBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, toke_);
      }
      if (timestamp_ != 0L) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(3, timestamp_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.lym.protobuf.AuthenticateRequestProto.AuthenticateRequest)) {
        return super.equals(obj);
      }
      com.lym.protobuf.AuthenticateRequestProto.AuthenticateRequest other = (com.lym.protobuf.AuthenticateRequestProto.AuthenticateRequest) obj;

      boolean result = true;
      result = result && getUid()
          .equals(other.getUid());
      result = result && getToke()
          .equals(other.getToke());
      result = result && (getTimestamp()
          == other.getTimestamp());
      result = result && unknownFields.equals(other.unknownFields);
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + UID_FIELD_NUMBER;
      hash = (53 * hash) + getUid().hashCode();
      hash = (37 * hash) + TOKE_FIELD_NUMBER;
      hash = (53 * hash) + getToke().hashCode();
      hash = (37 * hash) + TIMESTAMP_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getTimestamp());
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.lym.protobuf.AuthenticateRequestProto.AuthenticateRequest parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.lym.protobuf.AuthenticateRequestProto.AuthenticateRequest parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.lym.protobuf.AuthenticateRequestProto.AuthenticateRequest parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.lym.protobuf.AuthenticateRequestProto.AuthenticateRequest parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.lym.protobuf.AuthenticateRequestProto.AuthenticateRequest parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.lym.protobuf.AuthenticateRequestProto.AuthenticateRequest parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.lym.protobuf.AuthenticateRequestProto.AuthenticateRequest parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.lym.protobuf.AuthenticateRequestProto.AuthenticateRequest parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.lym.protobuf.AuthenticateRequestProto.AuthenticateRequest parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.lym.protobuf.AuthenticateRequestProto.AuthenticateRequest parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.lym.protobuf.AuthenticateRequestProto.AuthenticateRequest parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.lym.protobuf.AuthenticateRequestProto.AuthenticateRequest parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.lym.protobuf.AuthenticateRequestProto.AuthenticateRequest prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code com.lym.protobuf.AuthenticateRequest}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:com.lym.protobuf.AuthenticateRequest)
        com.lym.protobuf.AuthenticateRequestProto.AuthenticateRequestOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.lym.protobuf.AuthenticateRequestProto.internal_static_com_lym_protobuf_AuthenticateRequest_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.lym.protobuf.AuthenticateRequestProto.internal_static_com_lym_protobuf_AuthenticateRequest_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.lym.protobuf.AuthenticateRequestProto.AuthenticateRequest.class, com.lym.protobuf.AuthenticateRequestProto.AuthenticateRequest.Builder.class);
      }

      // Construct using com.lym.protobuf.AuthenticateRequestProto.AuthenticateRequest.newBuilder()
      public Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        uid_ = "";

        toke_ = "";

        timestamp_ = 0L;

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.lym.protobuf.AuthenticateRequestProto.internal_static_com_lym_protobuf_AuthenticateRequest_descriptor;
      }

      public com.lym.protobuf.AuthenticateRequestProto.AuthenticateRequest getDefaultInstanceForType() {
        return com.lym.protobuf.AuthenticateRequestProto.AuthenticateRequest.getDefaultInstance();
      }

      public com.lym.protobuf.AuthenticateRequestProto.AuthenticateRequest build() {
        com.lym.protobuf.AuthenticateRequestProto.AuthenticateRequest result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.lym.protobuf.AuthenticateRequestProto.AuthenticateRequest buildPartial() {
        com.lym.protobuf.AuthenticateRequestProto.AuthenticateRequest result = new com.lym.protobuf.AuthenticateRequestProto.AuthenticateRequest(this);
        result.uid_ = uid_;
        result.toke_ = toke_;
        result.timestamp_ = timestamp_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.lym.protobuf.AuthenticateRequestProto.AuthenticateRequest) {
          return mergeFrom((com.lym.protobuf.AuthenticateRequestProto.AuthenticateRequest)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.lym.protobuf.AuthenticateRequestProto.AuthenticateRequest other) {
        if (other == com.lym.protobuf.AuthenticateRequestProto.AuthenticateRequest.getDefaultInstance()) return this;
        if (!other.getUid().isEmpty()) {
          uid_ = other.uid_;
          onChanged();
        }
        if (!other.getToke().isEmpty()) {
          toke_ = other.toke_;
          onChanged();
        }
        if (other.getTimestamp() != 0L) {
          setTimestamp(other.getTimestamp());
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.lym.protobuf.AuthenticateRequestProto.AuthenticateRequest parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.lym.protobuf.AuthenticateRequestProto.AuthenticateRequest) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private java.lang.Object uid_ = "";
      /**
       * <code>string uid = 1;</code>
       */
      public java.lang.String getUid() {
        java.lang.Object ref = uid_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          uid_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string uid = 1;</code>
       */
      public com.google.protobuf.ByteString
          getUidBytes() {
        java.lang.Object ref = uid_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          uid_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string uid = 1;</code>
       */
      public Builder setUid(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        uid_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string uid = 1;</code>
       */
      public Builder clearUid() {
        
        uid_ = getDefaultInstance().getUid();
        onChanged();
        return this;
      }
      /**
       * <code>string uid = 1;</code>
       */
      public Builder setUidBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        uid_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object toke_ = "";
      /**
       * <code>string toke = 2;</code>
       */
      public java.lang.String getToke() {
        java.lang.Object ref = toke_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          toke_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string toke = 2;</code>
       */
      public com.google.protobuf.ByteString
          getTokeBytes() {
        java.lang.Object ref = toke_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          toke_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string toke = 2;</code>
       */
      public Builder setToke(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        toke_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string toke = 2;</code>
       */
      public Builder clearToke() {
        
        toke_ = getDefaultInstance().getToke();
        onChanged();
        return this;
      }
      /**
       * <code>string toke = 2;</code>
       */
      public Builder setTokeBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        toke_ = value;
        onChanged();
        return this;
      }

      private long timestamp_ ;
      /**
       * <code>int64 timestamp = 3;</code>
       */
      public long getTimestamp() {
        return timestamp_;
      }
      /**
       * <code>int64 timestamp = 3;</code>
       */
      public Builder setTimestamp(long value) {
        
        timestamp_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int64 timestamp = 3;</code>
       */
      public Builder clearTimestamp() {
        
        timestamp_ = 0L;
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFieldsProto3(unknownFields);
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:com.lym.protobuf.AuthenticateRequest)
    }

    // @@protoc_insertion_point(class_scope:com.lym.protobuf.AuthenticateRequest)
    private static final com.lym.protobuf.AuthenticateRequestProto.AuthenticateRequest DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.lym.protobuf.AuthenticateRequestProto.AuthenticateRequest();
    }

    public static com.lym.protobuf.AuthenticateRequestProto.AuthenticateRequest getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<AuthenticateRequest>
        PARSER = new com.google.protobuf.AbstractParser<AuthenticateRequest>() {
      public AuthenticateRequest parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new AuthenticateRequest(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<AuthenticateRequest> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<AuthenticateRequest> getParserForType() {
      return PARSER;
    }

    public com.lym.protobuf.AuthenticateRequestProto.AuthenticateRequest getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_lym_protobuf_AuthenticateRequest_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_lym_protobuf_AuthenticateRequest_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\036AuthenticateRequestProto.proto\022\020com.ly" +
      "m.protobuf\"C\n\023AuthenticateRequest\022\013\n\003uid" +
      "\030\001 \001(\t\022\014\n\004toke\030\002 \001(\t\022\021\n\ttimestamp\030\003 \001(\003B" +
      ",\n\020com.lym.protobufB\030AuthenticateRequest" +
      "Protob\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_com_lym_protobuf_AuthenticateRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_lym_protobuf_AuthenticateRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_lym_protobuf_AuthenticateRequest_descriptor,
        new java.lang.String[] { "Uid", "Toke", "Timestamp", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}