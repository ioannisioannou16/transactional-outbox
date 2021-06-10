#!/bin/sh

topic_arn=$(awslocal sns create-topic --name events_topic --output text)
echo "Topic ARN: $topic_arn"

queue_arn_1=$(awslocal sqs create-queue --queue-name consumer_1_queue --output text)
echo "Queue ARN 1: $queue_arn_1"

queue_arn_2=$(awslocal sqs create-queue --queue-name consumer_2_queue --output text)
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