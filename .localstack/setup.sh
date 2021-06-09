#!/bin/sh

topic_arn=$(awslocal sns create-topic --name test_topic --output text)
echo "Topic ARN: $topic_arn"

queue_arn_1=$(awslocal sqs create-queue --queue-name test_queue_1 --output text)
echo "Queue ARN 1: $queue_arn_1"

queue_arn_2=$(awslocal sqs create-queue --queue-name test_queue_2 --output text)
echo "Queue ARN 2: $queue_arn_2"

subscription_arn_1=$(awslocal sns subscribe \
    --topic-arn "$topic_arn" \
    --protocol sqs \
    --notification-endpoint "$queue_arn_1" \
    --attributes '{"FilterPolicy":"{\"eventType\":[\"EmployeeCreated\"]}"}' \
    --output text)

echo "Subscription ARN 1: $subscription_arn_1"

subscription_arn_2=$(awslocal sns subscribe \
    --topic-arn "$topic_arn" \
    --protocol sqs \
    --notification-endpoint "$queue_arn_2" \
    --attributes '{"FilterPolicy":"{\"eventType\":[\"EmployeeDeleted\"]}"}' \
    --output text)

echo "Subscription ARN 2: $subscription_arn_2"