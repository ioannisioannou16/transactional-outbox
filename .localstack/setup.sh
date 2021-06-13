#!/bin/sh

topic_arn=$(awslocal sns create-topic --name employee_events_topic.fifo --attribute FifoTopic=true,ContentBasedDeduplication=true --output text)
echo "Topic ARN: $topic_arn"

queue_arn_1=$(awslocal sqs create-queue --queue-name consumer_1_queue.fifo --attribute FifoQueue=true --output text)
echo "Queue ARN 1: $queue_arn_1"

queue_arn_2=$(awslocal sqs create-queue --queue-name consumer_2_queue.fifo --attribute FifoQueue=true --output text)
echo "Queue ARN 2: $queue_arn_2"

subscription_arn_1=$(awslocal sns subscribe \
    --topic-arn "$topic_arn" \
    --protocol sqs \
    --notification-endpoint "$queue_arn_1" \
    --attributes '{"FilterPolicy":"{\"eventType\":[\"EmployeeCreated\", \"EmployeeUpdated\"]}"}' \
    --output text)

echo "Subscription ARN 1: $subscription_arn_1"

subscription_arn_2=$(awslocal sns subscribe \
    --topic-arn "$topic_arn" \
    --protocol sqs \
    --notification-endpoint "$queue_arn_2" \
    --attributes '{"FilterPolicy":"{\"eventType\":[\"EmployeeDeleted\"]}"}' \
    --output text)

echo "Subscription ARN 2: $subscription_arn_2"